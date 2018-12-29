import { expectSaga } from 'redux-saga-test-plan';
import * as matchers from 'redux-saga-test-plan/matchers';
import { throwError } from 'redux-saga-test-plan/providers';
// API Service
import * as api from '../../../utils/api';
import {fetchAPIVersion} from '../index';
import apiServiceReducer from '../reducer';

describe('API Service:fetch version', () => {
  it('success', () => {
    const version = '123';
    const response = new Promise((resolve) => {
      resolve({
        ok: true, 
        json: function() { 
          return {version}
        }
      });
    });
    return expectSaga(fetchAPIVersion)
      .withReducer(apiServiceReducer)
      .provide([
        [matchers.call.fn(api.get), response],
      ])
      .hasFinalState({
        version,
      })
      .run();
  });
  it('failure', () => {
    const error ={
      message: 'an unknown error',
    }
    return expectSaga(fetchAPIVersion)
      .withReducer(apiServiceReducer)
      .provide([
        [matchers.call.fn(api.get), throwError(error)],
      ])
      .hasFinalState({
        version: error.message,
      })
      .run();
  });
});
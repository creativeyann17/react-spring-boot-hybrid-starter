import * as axios from "axios";
import { expectSaga } from 'redux-saga-test-plan';
import { select, call } from 'redux-saga/effects';
import * as helper from '../helper';
import * as sagas from '../index';
import * as selectors from '../selectors';
import { API_CSRF_HEADER } from '../../../utils/constants';
import reducer, { initialState } from '../reducer';


// Mock out all top level functions, such as get, put, delete and post:
jest.mock("axios");

const defaultProvider = (config, response) => [
  [select(selectors.csrf), config.csrf],
  [call(axios.request, config), Promise.resolve(response)],
];

describe('login', () => {
  it('success', () => {
    const params = { username: 'foo', password: 'bar' };
    const config = helper.buildFetch('POST', '/login', null, params);
    const response = { status: 200, headers: { [API_CSRF_HEADER]: 'csrf' } };
    return expectSaga(sagas.watchLoginRequest, { login: params })
      .withReducer(reducer)
      .provide(defaultProvider(config, response))
      .hasFinalState({
        ...initialState,
        login: params.username,
        csrf: response.headers[API_CSRF_HEADER]
      })
      .run();
  });

  it('failure', () => {
    const params = { username: 'foo', password: 'bar' };
    const config = helper.buildFetch('POST', '/login', null, params);
    const response = { status: 403, statusText: 'forbidden' };
    return expectSaga(sagas.watchLoginRequest, { login: params })
      .withReducer(reducer)
      .provide(defaultProvider(config, response))
      .hasFinalState({
        ...initialState,
        login: response.statusText,
      })
      .run();
  });
});

describe('version', () => {
  it('success', () => {
    const config = helper.buildFetch('GET', '/version');
    const response = { status: 200, data: { version: 'foo' } };
    return expectSaga(sagas.watchVersionRequest)
      .withReducer(reducer)
      .provide(defaultProvider(config, response))
      .hasFinalState({
        ...initialState,
        version: response.data.version,
      })
      .run();
  });

  it('failure', () => {
    const config = helper.buildFetch('GET', '/version');
    const response = { status: 403, statusText: 'forbidden' };
    return expectSaga(sagas.watchVersionRequest)
      .withReducer(reducer)
      .provide(defaultProvider(config, response))
      .hasFinalState({
        ...initialState,
        version: response.statusText,
      })
      .run();
  });
});
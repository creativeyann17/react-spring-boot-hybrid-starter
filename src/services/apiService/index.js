import { call, put, takeLatest } from 'redux-saga/effects'
import * as actions from './actions';
import * as actionTypes from './actionTypes';
import {get} from '../../utils/api';

function* fetchAPIVersion() {
  try {
    const response = yield call(get, '/version');
    const json = yield response.json()
    yield put(actions.apiServiceVersionSuccess(json.version));
  } catch (e) {
    yield put(actions.apiServiceVersionFailure(e.message));
  }
}

export default function* watchAsync() {
  yield takeLatest(actionTypes.API_SERVICE_VERSION_REQUEST, fetchAPIVersion);
}
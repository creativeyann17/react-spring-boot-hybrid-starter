import { call, put, takeLatest } from 'redux-saga/effects'
import * as actions from './actions';
import * as actionTypes from './actionTypes';
import * as helper from './helper';

export function* fetchAPIVersion() {
  try {
    const response = yield call(helper.get, '/version');
    yield put(actions.apiServiceVersionSuccess(response.data.version));
  } catch (e) {
    yield put(actions.apiServiceVersionFailure(e.message));
  }
}

export function* login(action) {
  try {
    yield call(helper.post, '/login', action.login);
    yield put(actions.apiServiceLoginSuccess(action.login));
  } catch (e) {
    yield put(actions.apiServiceLoginFailure(e.message));
  }
}

export function* logOut() {
  yield call(helper.post, '/logout');
}

export default function* watchAsync() {
  yield takeLatest(actionTypes.API_SERVICE_LOGIN_REQUEST, login);
  yield takeLatest(actionTypes.API_SERVICE_LOGIN_SUCCESS, fetchAPIVersion);
  yield takeLatest([actionTypes.API_SERVICE_VERSION_SUCCESS,actionTypes.API_SERVICE_VERSION_FAILURE], logOut);
}
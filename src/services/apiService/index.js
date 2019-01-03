import { call, put, takeLatest } from 'redux-saga/effects'
import * as actions from './actions';
import * as actionTypes from './actionTypes';
import {post, get} from '../../utils/api';

export function* fetchAPIVersion() {
  try {
    const response = yield call(get, '/version');
    yield put(actions.apiServiceVersionSuccess(response.data.version));
  } catch (e) {
    yield put(actions.apiServiceVersionFailure(e.message));
  }
}

export function* login(action) {
  try {
    yield call(post, '/login', action.login);
    yield put(actions.apiServiceLoginSuccess(action.login));
  } catch (e) {
    yield put(actions.apiServiceLoginFailure(e.message));
  }
}

export function* logOut() {
  yield call(post, '/logout');
}

export default function* watchAsync() {
  yield takeLatest(actionTypes.API_SERVICE_LOGIN_REQUEST, login);
  yield takeLatest(actionTypes.API_SERVICE_LOGIN_SUCCESS, fetchAPIVersion);
  yield takeLatest([actionTypes.API_SERVICE_VERSION_SUCCESS,actionTypes.API_SERVICE_VERSION_FAILURE], logOut);
}
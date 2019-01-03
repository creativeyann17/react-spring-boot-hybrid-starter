import { call, put, takeLatest } from 'redux-saga/effects'
import { delay } from 'redux-saga'
import * as actions from './actions';
import * as actionTypes from './actionTypes';
import * as helper from './helper';
import { WS_SERVICE_ON_MESSAGE } from '../wsService/actionTypes';

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

export function* logout() {
  try {
    yield delay(60000);
    yield call(helper.post, '/logout');
    yield put(actions.apiServiceLogoutSuccess());
  } catch (e) {
    yield put(actions.apiServiceLogoutFailure(e.message));
  }
}

export function* watchWsMessage({ data }) {
  switch (data.type) {
    case 'ONLINE_USERS_COUNT':
      yield put(actions.apiServiceOnlineUsersCount(data.count));
      break;
    default:
      break;
  }
}

export default function* watchAsync() {
  yield takeLatest(actionTypes.API_SERVICE_LOGIN_REQUEST, login);
  yield takeLatest(actionTypes.API_SERVICE_LOGIN_SUCCESS, fetchAPIVersion);
  yield takeLatest(WS_SERVICE_ON_MESSAGE, watchWsMessage);
  yield takeLatest([actionTypes.API_SERVICE_VERSION_SUCCESS, actionTypes.API_SERVICE_VERSION_FAILURE], logout);
}
import { call, put, takeLatest } from 'redux-saga/effects';
import { delay } from 'redux-saga';
import * as actions from './actions';
import * as actionTypes from './actionTypes';
import * as helper from './helper';
import { WS_MESSAGE_TYPES } from '../../utils/constants';
import { WS_SERVICE_ON_MESSAGE } from '../wsService/actionTypes';

export function* watchVersionRequest() {
  try {
    const response = yield call(helper.get, '/version');
    yield put(actions.apiServiceVersionSuccess(response.data.version));
  } catch (e) {
    yield put(actions.apiServiceVersionFailure(e.message));
  }
}

export function* watchLoginRequest(action) {
  try {
    yield call(helper.post, '/login', action.login);
    yield put(actions.apiServiceLoginSuccess(action.login));
  } catch (e) {
    yield put(actions.apiServiceLoginFailure(e.message));
  }
}

export function* watchLogoutRequest() {
  try {
    yield delay(60000);
    yield call(helper.post, '/logout');
    yield put(actions.apiServiceLogoutSuccess());
  } catch (e) {
    yield put(actions.apiServiceLogoutFailure(e.message));
  }
}

export function* watchWsOnMessage({ data }) {
  switch (data.type) {
    case WS_MESSAGE_TYPES.ONLINE_USERS_COUNT:
      yield put(actions.apiServiceOnlineUsersCount(data.count));
      break;
    default:
      break;
  }
}

export default function* watchAsync() {
  yield takeLatest(actionTypes.API_SERVICE_LOGIN_REQUEST, watchLoginRequest);
  yield takeLatest(
    [actionTypes.API_SERVICE_VERSION_REQUEST, actionTypes.API_SERVICE_LOGIN_SUCCESS],
    watchVersionRequest
  );
  yield takeLatest(WS_SERVICE_ON_MESSAGE, watchWsOnMessage);
  yield takeLatest(
    [
      actionTypes.API_SERVICE_LOGOUT_REQUEST,
      actionTypes.API_SERVICE_VERSION_SUCCESS,
      actionTypes.API_SERVICE_VERSION_FAILURE,
    ],
    watchLogoutRequest
  );
}

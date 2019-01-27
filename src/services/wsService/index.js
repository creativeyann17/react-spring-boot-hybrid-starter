import { take, fork, put, select, takeLatest } from 'redux-saga/effects';
import { eventChannel } from 'redux-saga';
import get from 'lodash/get';
import * as actions from './actions';
import * as actionTypes from './actionTypes';
import { API_SERVICE_LOGIN_SUCCESS } from '../apiService/actionTypes';
import * as selectors from './selectors';
import { DEBUG, API_WS_URL } from '../../utils/constants';

const buildUrl = path => {
  const protocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
  const host = DEBUG ? 'localhost:8080' : window.location.host; // until package proxy fixed for ws
  return new URL(path, protocol + host);
};

const isOpen = socket => {
  return get(socket, 'readyState', socket.CLOSED) !== socket.CLOSED;
};

function* watchOnOpen(socket) {
  const chan = eventChannel(emitter => {
    socket.onopen = event => emitter(event);
    return () => {};
  });
  while (isOpen(socket)) {
    const event = yield take(chan);
    yield put(actions.wsServiceOnOpen(event.target));
  }
}

function* watchOnMessage(socket) {
  const chan = eventChannel(emitter => {
    socket.onmessage = event => emitter(event);
    return () => {};
  });
  while (isOpen(socket)) {
    const event = yield take(chan);
    const data = JSON.parse(event.data);
    yield put(actions.wsServiceOnMessage(data));
  }
}

function* watchOnError(socket) {
  const chan = eventChannel(emitter => {
    socket.onerror = event => emitter(event);
    return () => {};
  });
  while (isOpen(socket)) {
    const event = yield take(chan);
    yield put(actions.wsServiceOnError(event.code));
  }
}

function* watchOnClose(socket) {
  const chan = eventChannel(emitter => {
    socket.onclose = event => emitter(event);
    return () => {};
  });
  while (isOpen(socket)) {
    const event = yield take(chan);
    yield put(actions.wsServiceOnClose(event.target));
  }
}

export function* watchOpen() {
  const existingWs = yield select(selectors.ws);
  if (!existingWs) {
    const ws = new WebSocket(buildUrl(API_WS_URL));
    yield fork(watchOnOpen, ws);
    yield fork(watchOnMessage, ws);
    yield fork(watchOnError, ws);
    yield fork(watchOnClose, ws);
  }
}

export default function* watchAsync() {
  yield takeLatest([actionTypes.WS_SERVICE_OPEN, API_SERVICE_LOGIN_SUCCESS], watchOpen);
}

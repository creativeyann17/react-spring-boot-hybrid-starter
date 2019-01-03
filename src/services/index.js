import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects'
// all reducers
import intlServiceReducer from './intlService/reducer';
import apiServiceReducer from './apiService/reducer';
import wsServiceReducer from './wsService/reducer';
// all sagas
import apiServiceSaga from './apiService'
import wsServiceSaga from './wsService'

export const rootReducer = combineReducers({
  intlServiceReducer,
  apiServiceReducer,
  wsServiceReducer,
});

export function* rootSaga() {
  yield all([
    apiServiceSaga(),
    wsServiceSaga(),
  ])
}

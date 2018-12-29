import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects'
// all reducers
import intlServiceReducer from './intlService/reducer';
import apiServiceReducer from './apiService/reducer';
// all sagas
import apiServiceSaga from './apiService'

export const rootReducer = combineReducers({
  intlServiceReducer,
  apiServiceReducer,
});

export function* rootSaga() {
  yield all([
    apiServiceSaga(),
  ])
}

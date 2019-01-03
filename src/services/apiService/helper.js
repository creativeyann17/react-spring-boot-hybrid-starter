import axios from 'axios';
import _get from 'lodash/get';
import { call, put, select } from 'redux-saga/effects'
import * as actions from './actions';
import * as apiSelectors from './selectors';
import {API_BASE_URL, DEBUG, API_CSRF_HEADER} from '../../utils/constants';

function* handleCsrfToken(response, currentCsrfHeaderToken) {
  const newCsrfHeaderToken= _get(response.headers, API_CSRF_HEADER, currentCsrfHeaderToken);
  if (newCsrfHeaderToken !== currentCsrfHeaderToken){
    yield put(actions.apiServiceCsrf(newCsrfHeaderToken));
    if(DEBUG){
      console.info("CSRF Token changed: ", newCsrfHeaderToken);
    }
  }
}

const handleError = (response) => {
  if (response.status !== 200) {
    throw Error(response.statusText);
  }
  return response;
}

export const buildFetch = (method, url, csrf, params = undefined, data = undefined) =>{
  const config = {
    baseURL: API_BASE_URL,
    url,
    method,
    params,
    data,
  };
  if (csrf) {
    config.headers = {[API_CSRF_HEADER]: csrf};
  }
  return config;
}

function* fetch(method, url, params, data){
  try{
    const csrf = yield select(apiSelectors.csrf);
    const fetchConfig = buildFetch(method, url, csrf, params, data);
    const response = yield call(axios.request, fetchConfig);
    yield call(handleCsrfToken, response, csrf);
    return handleError(response);
  }catch(error){
    throw error;
  }
}

export const get = (url, params) => {
  return fetch('GET', url, params);
};

export const post = (url, params, data) => {
  return fetch('POST', url, params, data);
};

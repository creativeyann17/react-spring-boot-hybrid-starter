import axios from 'axios';
import _get from 'lodash/get';
import {API_BASE_URL} from '../utils/constants';
import {DEBUG} from './constants';

let csrfSettings ={
  header: 'x-csrf-token',
  token: null,
}

const handleCsrfToken= (response) => {
  const csrfHeaderToken= _get(response.headers, csrfSettings.header, csrfSettings.token);
  if (csrfHeaderToken !== csrfSettings.token){
    csrfSettings.token = csrfHeaderToken;
    if(DEBUG){
      console.info("CSRF Token changed: ", csrfSettings);
    }
  }
}

const handleError = (response) => {
  if (response.status !== 200) {
    throw Error(response.statusText);
  }
  return response;
}

const genericFetch = (method, url, params = null, data = null) => {
  return axios({
    baseURL: API_BASE_URL,
    url,
    headers: {[csrfSettings.header]: csrfSettings.token},
    method,
    params,
    data,
  })
  .then((response) => {
    handleCsrfToken(response);
    return handleError(response);
  })
  .catch((error) => {
      throw error;
  });
}

export const post = (url, params) => {
  return genericFetch('POST', url, params);
};

export const get= (url, params) => {
  return genericFetch('GET', url, params);
};

export const postJSON = (url, body) => {
  return genericFetch('POST', url, null, body);
};

export const getJSON = (url, body) => {
  return genericFetch('GET', url, null, body);
};
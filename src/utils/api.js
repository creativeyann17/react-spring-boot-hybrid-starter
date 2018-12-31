import axios from 'axios';
import urljoin from 'url-join';
import {API_BASE_URL} from '../utils/constants';

const genericFetch = (method, url, params = null, data = null) => {
  return axios({
    url: urljoin(API_BASE_URL, url),
    method,
    params,
    data,
  })
  .then((response) => {
    if (response.status !== 200) {
      throw Error(response.statusText);
    }
    return response;
  })
  .catch((error) => {
      throw error;
  });
}

export const post = (url, params) => {
  return genericFetch('POST', url , params);
};

export const get= (url, params) => {
  return genericFetch('GET', url , params);
};

export const postJSON = (url, body) => {
  return genericFetch('POST', url , null, body);
};

export const getJSON = (url, body) => {
  return genericFetch('GET', url , null, body);
};
import {API_BASE_URL} from '../utils/constants';
var urljoin = require('url-join');

function handleErrors(response) {
  if (!response.ok) {
      throw Error(response.statusText);
  }
  return response;
}

export const get = (url, params = '') => {
    return fetch(urljoin(API_BASE_URL, url), {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      params,
    })
    .then(handleErrors)
    .catch(function(error) {
        throw error;
    });
};
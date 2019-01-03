export const DEFAULT_LANGUAGE = navigator.language.split(/[-_]/)[0];

export const DEBUG = process.env.NODE_ENV === 'development';

export const API_CSRF_HEADER = 'x-csrf-token';
export const API_BASE_URL = '/api';
export const API_WS_URL = API_BASE_URL + '/ws';
export const API_GUEST = {
  username: 'GUEST',
  password: ''
}

export const ROUTES = {
  INDEX: '/',
}
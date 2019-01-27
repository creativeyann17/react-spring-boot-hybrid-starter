import * as actionTypes from './actionTypes';

export const apiServiceVersionRequest = () => {
  return {
    type: actionTypes.API_SERVICE_VERSION_REQUEST,
  };
};

export const apiServiceVersionSuccess = version => {
  return {
    type: actionTypes.API_SERVICE_VERSION_SUCCESS,
    version,
  };
};

export const apiServiceVersionFailure = error => {
  return {
    type: actionTypes.API_SERVICE_VERSION_FAILURE,
    error,
  };
};

export const apiServiceLogoutRequest = () => {
  return {
    type: actionTypes.API_SERVICE_LOGOUT_REQUEST,
  };
};

export const apiServiceLogoutSuccess = login => {
  return {
    type: actionTypes.API_SERVICE_LOGOUT_SUCCESS,
  };
};

export const apiServiceLogoutFailure = error => {
  return {
    type: actionTypes.API_SERVICE_LOGOUT_FAILURE,
    error,
  };
};

export const apiServiceLoginRequest = login => {
  return {
    type: actionTypes.API_SERVICE_LOGIN_REQUEST,
    login,
  };
};

export const apiServiceLoginSuccess = login => {
  return {
    type: actionTypes.API_SERVICE_LOGIN_SUCCESS,
    login,
  };
};

export const apiServiceLoginFailure = error => {
  return {
    type: actionTypes.API_SERVICE_LOGIN_FAILURE,
    error,
  };
};

export const apiServiceCsrf = csrf => {
  return {
    type: actionTypes.API_SERVICE_CSRF,
    csrf,
  };
};

export const apiServiceOnlineUsersCount = count => {
  return {
    type: actionTypes.API_SERVICE_ONLINE_USERS_COUNT,
    count,
  };
};

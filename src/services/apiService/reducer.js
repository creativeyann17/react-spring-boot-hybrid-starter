import * as actionTypes from './actionTypes';

export const initialState = {
  version: undefined,
  login: undefined,
  csrf: undefined,
  onlineUserCount: undefined,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case actionTypes.API_SERVICE_VERSION_REQUEST:
      return {
        ...state,
        version: '...',
      };
    case actionTypes.API_SERVICE_VERSION_SUCCESS:
      return {
        ...state,
        version: action.version,
      };
    case actionTypes.API_SERVICE_VERSION_FAILURE:
      return {
        ...state,
        version: action.error,
      };
    case actionTypes.API_SERVICE_LOGIN_REQUEST:
      return {
        ...state,
        login: '...',
      };
    case actionTypes.API_SERVICE_LOGIN_SUCCESS:
      return {
        ...state,
        login: action.login.username,
      };
    case actionTypes.API_SERVICE_LOGIN_FAILURE:
      return {
        ...state,
        login: action.error,
      };
    case actionTypes.API_SERVICE_LOGOUT_SUCCESS:
      return {
        ...state,
        login: undefined,
      };
    case actionTypes.API_SERVICE_CSRF:
      return {
        ...state,
        csrf: action.csrf,
      };
    case actionTypes.API_SERVICE_ONLINE_USERS_COUNT:
      return {
        ...state,
        onlineUserCount: action.count,
      };
    default:
      return state;
  }
}
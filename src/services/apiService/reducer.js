import * as actionTypes from './actionTypes';

const initialState = {
    version: null,
    login: null,
};

export default function(state = initialState, action) {
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
      default:
          return state;
    } 
}
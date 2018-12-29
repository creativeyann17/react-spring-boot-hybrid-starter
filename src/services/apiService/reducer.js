import * as actionTypes from './actionTypes';

const initialState = {
    version: null,
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
      default:
          return state;
    } 
}
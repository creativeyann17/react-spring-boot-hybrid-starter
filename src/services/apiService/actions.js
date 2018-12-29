import * as actionTypes from './actionTypes';

export const apiServiceVersionRequest = () => {
  return {
    type: actionTypes.API_SERVICE_VERSION_REQUEST
  }
}

export const apiServiceVersionSuccess = (version) => {
  return {
    type: actionTypes.API_SERVICE_VERSION_SUCCESS,
    version,
  }
}

export const apiServiceVersionFailure = (error) => {
  return {
    type: actionTypes.API_SERVICE_VERSION_FAILURE,
    error,
  }
}
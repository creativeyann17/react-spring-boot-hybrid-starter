import * as actionTypes from './actionTypes';

export const wsServiceOpen = () => {
  return {
    type: actionTypes.WS_SERVICE_OPEN
  }
}

export const wsServiceOnOpen = (ws) => {
  return {
    type: actionTypes.WS_SERVICE_ON_OPEN,
    ws
  }
}

export const wsServiceOnMessage = (data) => {
  return {
    type: actionTypes.WS_SERVICE_ON_MESSAGE,
    data
  }
}

export const wsServiceOnError = (error) => {
  return {
    type: actionTypes.WS_SERVICE_ON_ERROR,
    error
  }
}

export const wsServiceOnClose = (ws) => {
  return {
    type: actionTypes.WS_SERVICE_ON_CLOSE,
    ws
  }
}
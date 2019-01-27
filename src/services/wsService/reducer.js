import * as actionTypes from './actionTypes';

export const initialState = {
  ws: undefined,
};

export default function(state = initialState, action) {
  switch (action.type) {
    case actionTypes.WS_SERVICE_ON_OPEN:
      return {
        ...state,
        ws: action.ws,
      };
    case actionTypes.WS_SERVICE_ON_CLOSE:
      return {
        ...state,
        ws: undefined,
      };
    default:
      return state;
  }
}

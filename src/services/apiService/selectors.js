export const csrf = state => state.apiServiceReducer.csrf;
export const version = state => state.apiServiceReducer.version;
export const login = state => state.apiServiceReducer.login;
export const onlineUserCount = state =>
  state.apiServiceReducer.onlineUserCount
    ? `${state.apiServiceReducer.onlineUserCount}`
    : undefined;

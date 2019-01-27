import React from 'react';
import ReactDOM from 'react-dom';

// redux + saga
import { Provider } from 'react-redux';
import { applyMiddleware, createStore } from 'redux';
import { logger } from 'redux-logger';
import createSagaMiddleware from 'redux-saga';
import { rootReducer, rootSaga } from './services';

// others
import * as serviceWorker from './utils/serviceWorker';
import App from './app';
import { DEBUG } from './utils/constants';
import './index.css';

const sagaMiddleware = createSagaMiddleware();
const middlewares = [sagaMiddleware];
if (DEBUG) {
  middlewares.push(logger);
}
const store = createStore(rootReducer, applyMiddleware(...middlewares));
sagaMiddleware.run(rootSaga);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();

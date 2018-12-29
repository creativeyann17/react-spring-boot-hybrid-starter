import React from 'react';
import ReactDOM from 'react-dom';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import { rootReducer } from '../../services';
import App from '../index';

const store = createStore(rootReducer);

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render( <Provider store={store}><App /></Provider>, div);
  ReactDOM.unmountComponentAtNode(div);
});

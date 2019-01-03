import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { defineMessages, injectIntl, intlShape } from 'react-intl';
import DefaultLayout from '../../layouts/defaultLayout';
import * as apiSelectors from '../../services/apiService/selectors';

const messages = defineMessages({
  welcome: {
    id: 'page.index.welcome'
  },
  version: {
    id: 'page.index.version'
  },
  login: {
    id: 'page.index.login'
  }
})

const DefaultPage = (props) => {

  const {intl, version, login}= props;

  return (
    <DefaultLayout>
      <h1>{intl.formatMessage(messages.welcome)}</h1>
      <p>{intl.formatMessage(messages.login, {login})}</p>
      <p>{intl.formatMessage(messages.version, {version})}</p>
    </DefaultLayout>
  );
}

DefaultPage.propTypes = {
  intl: intlShape.isRequired,
  version: PropTypes.string,
  login: PropTypes.string,
}

DefaultPage.defaultProps = {
  version: 'N/A',
  login: 'N/A',
}

const mapStateToProps = (state) => {
  return {
    version: apiSelectors.version(state),
    login: apiSelectors.login(state),
  };
}

export default injectIntl(connect(mapStateToProps)(DefaultPage));
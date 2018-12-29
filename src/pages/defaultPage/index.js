import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { defineMessages, injectIntl, intlShape } from 'react-intl';
import DefaultLayout from '../../layouts/defaultLayout';
// selectors
import { getVersionState } from '../../services/apiService/selectors'

const messages = defineMessages({
  welcome: {
    id: 'page.index.welcome'
  },
  version: {
    id: 'page.index.version'
  }
})

const DefaultPage = (props) => {

  const {intl, version}= props;

  return (
    <DefaultLayout>
      <h1>{intl.formatMessage(messages.welcome)}</h1>
      <p>{intl.formatMessage(messages.version, {version: version})}</p>
    </DefaultLayout>
  );
}

DefaultPage.propTypes = {
  intl: intlShape.isRequired,
  version: PropTypes.string,
}

DefaultPage.defaultProps = {
  version: 'N/A',
}

const mapStateToProps = (state) => {
  return {
      version: getVersionState(state)
  };
}

export default injectIntl(connect(mapStateToProps)(DefaultPage));
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
// i18n
import { addLocaleData, IntlProvider } from 'react-intl';
import locale_en from 'react-intl/locale-data/en';
import locale_fr from 'react-intl/locale-data/fr';
import i18n_en from './i18n/en.json';
import i18n_fr from './i18n/fr.json';
// router
import { BrowserRouter, Route } from 'react-router-dom';
import DefaultPage from '../pages/defaultPage';
import { ROUTES } from '../utils/constants';
// selectors
import { getLangState } from '../services/intlService/selector';
// actions
import { apiServiceVersionRequest } from '../services/apiService/actions';

addLocaleData([...locale_en, ...locale_fr]);

export const messages = {
    'en': i18n_en,
    'fr': i18n_fr
};

class App extends Component {

  componentDidMount = () => {
    this.props.fetchAPIVersion();
  }

  render() {
    const {lang} = this.props;
    return (
      <React.Fragment>
        <IntlProvider locale={lang} messages={messages[lang]} >
          <BrowserRouter>
              <Route exact path={ROUTES.INDEX} component={DefaultPage} />
          </BrowserRouter>
        </IntlProvider>
      </React.Fragment>
    );
  }
}

App.propTypes = {
  lang: PropTypes.string.isRequired,
}

const mapStateToProps = (state) => {
  return {
      lang: getLangState(state)
  };
}

const mapDispatchToProps = dispatch => ({
  fetchAPIVersion: () => dispatch(apiServiceVersionRequest()),
})

export default connect(mapStateToProps,mapDispatchToProps)(App);

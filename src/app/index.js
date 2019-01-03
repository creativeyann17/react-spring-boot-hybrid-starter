import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
// i18n
import { addLocaleData, IntlProvider } from 'react-intl';
import locale_en from 'react-intl/locale-data/en';
import locale_fr from 'react-intl/locale-data/fr';
import i18n_en from '../i18n/en.json';
import i18n_fr from '../i18n/fr.json';
// router
import { BrowserRouter, Route } from 'react-router-dom';
import DefaultPage from '../pages/defaultPage';
import { ROUTES, API_GUEST } from '../utils/constants';
// actions
import { apiServiceLoginRequest } from '../services/apiService/actions';
// selectors
import * as intlSelectors from '../services/intlService/selectors';

addLocaleData([...locale_en, ...locale_fr]);

export const messages = {
  'en': i18n_en,
  'fr': i18n_fr
};

class App extends Component {

  componentDidMount = () => {
    this.props.logAsGuest();
  }

  render() {
    const { lang } = this.props;
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
    lang: intlSelectors.lang(state),
  };
}

const mapDispatchToProps = dispatch => ({
  logAsGuest: () => dispatch(apiServiceLoginRequest(API_GUEST)),
})

export default connect(mapStateToProps, mapDispatchToProps)(App);

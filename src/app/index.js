import React, { Component, lazy, Suspense } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
// i18n
import { addLocaleData, IntlProvider } from 'react-intl';
import localeEn from 'react-intl/locale-data/en';
import localeFr from 'react-intl/locale-data/fr';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import i18nEn from '../i18n/en.json';
import i18nFr from '../i18n/fr.json';
// router
import { ROUTES, API_GUEST } from '../utils/constants';
// actions
import { apiServiceLoginRequest } from '../services/apiService/actions';
// selectors
import * as intlSelectors from '../services/intlService/selectors';
import LoadingPage from '../pages/loadingPage';

addLocaleData([...localeEn, ...localeFr]);

const DefaultPage = lazy(() => import('../pages/defaultPage'));

export const messages = {
  en: i18nEn,
  fr: i18nFr,
};

class App extends Component {
  componentDidMount() {
    const { logAsGuest } = this.props;
    logAsGuest();
  }

  render() {
    const { lang } = this.props;
    return (
      <React.Fragment>
        <IntlProvider locale={lang} messages={messages[lang]}>
          <Suspense fallback={<LoadingPage />}>
            <BrowserRouter>
              <Switch>
                <Route exact path={ROUTES.INDEX} component={() => <DefaultPage />} />
              </Switch>
            </BrowserRouter>
          </Suspense>
        </IntlProvider>
      </React.Fragment>
    );
  }
}

App.propTypes = {
  lang: PropTypes.string.isRequired,
  logAsGuest: PropTypes.func.isRequired,
};

const mapStateToProps = state => {
  return {
    lang: intlSelectors.lang(state),
  };
};

const mapDispatchToProps = dispatch => ({
  logAsGuest: () => dispatch(apiServiceLoginRequest(API_GUEST)),
});

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);

import React from 'react';
import PropTypes from 'prop-types';

const DefaultLayout = props => {
  const { children } = props;
  return <div>{children}</div>;
};

DefaultLayout.propTypes = {
  children: PropTypes.arrayOf(PropTypes.node).isRequired,
};

export default DefaultLayout;

import { DEFAULT_LANGUAGE } from '../../utils/constants';

const initialState = {
  lang: DEFAULT_LANGUAGE,
};

export default function(state = initialState, action) {
  switch (action.type) {
    default:
      return state;
  }
}

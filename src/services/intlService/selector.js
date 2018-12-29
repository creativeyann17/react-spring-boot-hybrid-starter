import { createSelector } from 'reselect'

const lang = (state) => state.intlServiceReducer.lang

export const getLangState = createSelector([ lang ],
  (lang) => lang
)
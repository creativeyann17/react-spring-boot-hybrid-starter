import { createSelector } from 'reselect'

const version = (state) => state.apiServiceReducer.version

export const getVersionState = createSelector([ version ],
  (version) => version
)
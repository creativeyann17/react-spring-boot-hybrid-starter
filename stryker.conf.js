// This config was generated using a preset.
// Please see the handbook for more information: https://github.com/stryker-mutator/stryker-handbook/blob/master/stryker/guides/react.md#react
module.exports = function(config) {
  config.set({
    mutate: ['src/services/**/*.js?(x)', '!src/services/**/*@(.test|.spec|Spec).js?(x)'],
    mutator: 'javascript',
    testRunner: 'jest',
    reporters: ['progress', 'clear-text', 'html'],
    coverageAnalysis: 'off',
    jest: {
      projectType: 'react',
    },
  });
};

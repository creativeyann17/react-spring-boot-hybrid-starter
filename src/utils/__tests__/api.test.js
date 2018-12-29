import {get} from '../api';

describe("api functions", function () {
  it("success", function () {
    const json = {foo:"foo"};
    window.fetch = jest.fn().mockImplementation(() => Promise.resolve({ok: true, json: () => {return json}}));
    get('foo').then(response => {
      expect(response.ok).toBe(true);
      expect(response.json()).toBe(json);
    });
  });
  it("failure", function () {
    window.fetch = jest.fn().mockImplementation(() => Promise.resolve({ok: false, statusText:"error"}));
    get('foo').then(response => {
      expect(response.message).toBe("error");
    });
  });
});
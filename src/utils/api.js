function handleErrors(response) {
  if (!response.ok) {
      throw Error(response.statusText);
  }
  return response;
}

export const get = (url, params = '') => {
    return fetch(url, {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      params,
    })
    .then(handleErrors)
    .catch(function(error) {
        throw error;
    });
};
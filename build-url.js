function buildUrl(url, options) {
  let builtUrl = getBaseUrl(url, options);

  if (options) {
      builtUrl = addPath(builtUrl, options.path);
      builtUrl = addQueryParams(builtUrl, options.queryParams);
      builtUrl = addHash(builtUrl, options.hash);
  }

  return builtUrl;
}

function getBaseUrl(url, options) {
  if (!url || typeof url === 'object') {
      return '';
  }
  return url;
}

function addPath(url, path) {
  if (path) {
      return url + '/' + path;
  }
  return url;
}

function addQueryParams(url, queryParams) {
  if (queryParams) {
      let queryString = [];
      for (let key in queryParams) {
          if (queryParams.hasOwnProperty(key)) {
              queryString.push(key + '=' + queryParams[key]);
          }
      }
      return url + '?' + queryString.join('&');
  }
  return url;
}

function addHash(url, hash) {
  if (hash) {
      return url + '#' + hash;
  }
  return url;
}

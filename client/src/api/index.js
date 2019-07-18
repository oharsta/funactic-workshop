function validFetch(path, options, headers) {
  const contentHeaders = {
    "Accept": "application/json",
    "Content-Type": "application/json",
    ...headers
  };
  const fetchOptions = Object.assign({}, {headers: contentHeaders}, options, {
    credentials: "same-origin",
    redirect: "manual"
  });
  return fetch(path, fetchOptions);
}

function fetchJson(path, options = {}, headers = {}) {
  return validFetch(path, options, headers)
    .then(res => res.json());
}

// function postPutJson(path, body, method) {
//   return fetchJson(path, {method: method, body: JSON.stringify(body)});
// }
//
// function fetchDelete(path) {
//   return validFetch(path, {method: "delete"});
// }

//API
export function userByName(name) {
  return fetchJson(`/api/users/search?name=${encodeURIComponent(name)}`);
}

export function users() {
  return fetchJson("/api/users");
}

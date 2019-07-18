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

//API
export function userByName(name) {
  return fetchJson(`/api/users/search?name=${encodeURIComponent(name)}`);
}

export function users() {
  return fetchJson("/api/users");
}

export function saveUser(user) {
  return fetchJson("/api/users", {method: "POST", body: JSON.stringify(user)});
}

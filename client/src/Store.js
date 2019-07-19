// Store.js

import {decorate, observable} from "mobx";

class Store {
  currentUser = undefined;
}

decorate(Store, {
  currentUser: observable
});

export const store = new Store();

import React from "react";
import {observer} from "mobx-react";
import {store} from "./Store";
import {Redirect, Route} from "react-router-dom";

export const ProtectedRoute = observer(({...res}) => store.currentUser ?
  <Route {...res}/> : <Redirect to="/login"/>);

import React, {useEffect, useState} from 'react';
import './App.scss';
import {observer} from "mobx-react";
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import UserPage from "./pages/UserPage";
import {NewUser} from "./pages/NewUser";
import {ProtectedRoute} from "./ProtectedRoute";
import {Login} from "./pages/Login";
import {store} from "./Store";

const Index = () => {
  return <h2>Home</h2>;
};

const About = () => <h2>About</h2>;


const Users = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch("/api/users").then(res => res.json()).then(json => {
      // debugger;
      setUsers(json)
    })
  }, []);

  return <div><h2>Users</h2>{users.map(user => <p key={user.id}>{user.name}</p>)}</div>;
};

const AppRouter = observer(() => {
  return (
    <Router>
      <div className="app">
        {store.currentUser && <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about/">About</Link>
            </li>
            <li>
              <Link to="/users/">Users</Link>
            </li>
            <li>
              <Link to="/users-page/">Users-page</Link>
            </li>
            <li>
              <Link to="/new-user/">New User</Link>
            </li>
          </ul>
        </nav>}
        <Switch>
          <ProtectedRoute path="/" exact component={Index}/>
          <Route path="/login" exact component={Login}/>
          <ProtectedRoute path="/about/" component={About}/>
          <ProtectedRoute path="/users/" component={Users}/>
          <ProtectedRoute path="/users-page/" component={UserPage}/>
          <ProtectedRoute path="/new-user/" component={NewUser}/>
        </Switch>
      </div>
    </Router>
  );
});


export default AppRouter;

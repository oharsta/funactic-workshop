import React, {useEffect, useState} from 'react';
import './App.scss';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import UserPage from "./pages/UserPage";

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

function AppRouter() {
  return (
    <Router>
      <div className="app">
        <nav>
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
          </ul>
        </nav>
        <Switch>
          <Route path="/" exact component={Index}/>
          <Route path="/about/" component={About}/>
          <Route path="/users/" component={Users}/>
          <Route path="/users-page/" component={UserPage}/>
        </Switch>
      </div>
    </Router>
  );
}


export default AppRouter;

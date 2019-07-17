import React, {useEffect, useState} from 'react';
// import logo from './logo.svg';
import './App.scss';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";

const Index = () => {
  return <h2>Home</h2>;
};

const About = () => <h2>About</h2>;


const Users = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch("/csof/api/users").then(res => res.json()).then(json => {
      // debugger;
      setUsers(json)
    })
  }, []);

  return <div><h2>Users</h2>{users.map(user => <p key={user.id}>{user.name}</p>)}</div>;
};

function AppRouter() {
  return (
    <Router>
      <div>
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
          </ul>
        </nav>
        <Switch>
          <Route path="/" exact component={Index}/>
          <Route path="/about/" component={About}/>
          <Route path="/users/" component={Users}/>
        </Switch>
      </div>
    </Router>
  );
}

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo"/>
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

export default AppRouter; // App;

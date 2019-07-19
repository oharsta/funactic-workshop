import React, {useState} from 'react';
import {store} from "../Store";
import {Redirect} from "react-router-dom";
import {login} from "../api";

export const Login = () => {
  //Note: with this many state I would prefer normal React.Component
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [loginSuccess, setLoginSuccess] = useState(false);
  const [error, setError] = useState(false);

  const submit = e => {
    e.preventDefault();
    login(name, password)
      .then(user => {
        store.currentUser = user;
        setLoginSuccess(true);
      })
      .catch(() => setError(true));
  };

  if (loginSuccess) {
    return <Redirect to="/"/>
  }

  return (
    <div>
      <label htmlFor=" name">Name</label>
      <input type=" text" name=" name" value={name} onChange={e => setName(e.target.value)}/>
      <label htmlFor=" name">Password</label>
      <input type="password" name=" password" value={password} onChange={e => setPassword(e.target.value)}/>
      {error && <em>Wrong username / password</em>}
      <button type=" submit" onClick={submit}>Submit</button>
    </div>
  )

};

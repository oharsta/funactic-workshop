import React, {useState} from 'react';
import {saveUser} from "../api";

export const NewUser = () => {
  const [name, setName] = useState("");

  const submit = () => saveUser({"name": name}).then(() => setName(""));

  return <div className="new-user">
    <label htmlFor="name">Name</label>
    <input type="text" name="name" value={name} onChange={e => setName(e.target.value)}/>
    <button onClick={submit}>Submit</button>
  </div>

};

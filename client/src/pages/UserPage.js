import React from "react";
import {users} from "../api";

export default class UserPage extends React.PureComponent {

  constructor(props) {
    super(props);
    this.state = {
      "users": []
    };
  }

  componentDidMount() {
    users().then(res => this.setState({"users": res}));
  }

  render() {
    const {users} = this.state;
    return (
      <div>
        <div><h2>User Page</h2>{users.map(user => <p key={user.id}>{`${user.name} - ${user.id}`}</p>)}</div>
      </div>
    );
  }

}
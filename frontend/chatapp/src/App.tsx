import React from 'react';
import './App.css';
import { LoginForm } from './components/LoginForm';
import { Home } from './components/Home';
import { IUser } from './model/IUser';

export class App extends React.Component<IProps, IState> {
  constructor(props: IProps) {
    super(props);
    this.state = {
      loginUser: null
    };

    this.updateLoginUserId = this.updateLoginUserId.bind(this);
  }

  updateLoginUserId(user: IUser) {
    this.setState({ loginUser: user });
  }

  render() {
    return <div className="App">
      {this.state.loginUser === null
        ? <LoginForm loadHome={this.updateLoginUserId}></LoginForm>
        : <Home user={this.state.loginUser}></Home>}
    </div>
  }
}

interface IProps {
}

interface IState {
  loginUser: IUser | null;
}

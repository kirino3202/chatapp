import React from "react";
import { IUser } from "../model/IUser";

export class LoginForm extends React.Component<IProps, IState> {
  constructor(props: IProps) {
    super(props);

    this.state = {
      username: '',
      password: '',
      message: null
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
    this.handleRegisterThenLogin = this.handleRegisterThenLogin.bind(this);
    this.requestLogin = this.requestLogin.bind(this);
    this.requestRegisterThenLogin = this.requestRegisterThenLogin.bind(this);
  }

  handleChange(inputName: keyof IState) {
    return (event: React.ChangeEvent<HTMLInputElement>) => {
      this.setState({ [inputName]: event.target.value } as Pick<IState, keyof IState>);
    }
  }

  handleLogin(event: React.FormEvent<HTMLButtonElement>) {
    event.preventDefault();
    this.requestLogin();
  }

  handleRegisterThenLogin(event: React.FormEvent<HTMLButtonElement>) {
    event.preventDefault();
    this.requestRegisterThenLogin();
  }

  requestLogin() {
    const formData = new FormData();
    formData.append('username', this.state.username);
    formData.append('password', this.state.password);

    fetch('/api/login',
      {
        method: 'POST',
        body: formData
      }).then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          throw new Error(response.status.toString());
        }
      }).then((response) => {
        this.props.loadHome({
          id: response.userId,
          username: this.state.username
        });
      }).catch((e) => {
        this.setState({ 'message': 'ログイン失敗' });
        console.log(e);
      });
  }

  requestRegisterThenLogin() {
    const body = {
      username: this.state.username,
      password: this.state.password
    };

    fetch('/api/register', {
      method: 'POST',
      body: JSON.stringify(body),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then((response) => {
        if (response.status === 200) {
          this.requestLogin();
        } else {
          this.setState({ message: '登録失敗' });
        }
      });

  }

  render() {
    return <div><form>
      <label>
        Username:
        <input type="text" value={this.state.username} onChange={this.handleChange('username')}></input>
      </label>
      <label>
        Password:
        <input type="password" value={this.state.password} onChange={this.handleChange('password')}></input>
      </label>
      <button onClick={this.handleLogin}>ログイン</button>
      <button onClick={this.handleRegisterThenLogin}>登録してログイン</button>
    </form>
      <p>{this.state.message}</p>
    </div>
  }
}

interface IProps {
  loadHome: (user: IUser) => void;
}

interface IState {
  username: string;
  password: string;
  message: string | null;
}

import React from "react";
import { IChannel } from "../model/IChannel";
import { IMessage } from "../model/IMessage";
import { callBackendAPI } from "../callBackendAPI";
import { IUser } from "../model/IUser";
import './MessageList.css';

export class MessageList extends React.Component<IProps, IState> {
  constructor(props: IProps) {
    super(props);

    this.state = {
      messages: [],
      newMessageContent: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    callBackendAPI(`/api/channel/${this.props.channel.id}/message`, 'GET')
      .then(response => response.json())
      .then(response => {
        response.forEach((message: any) => message['createdAt'] = new Date(message['createdAt']));
        this.setState({
          messages: response
        })
      });
  }

  handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    this.setState({ newMessageContent: event.target.value });
  }

  handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    callBackendAPI(`/api/channel/${this.props.channel.id}/message`,
      'POST',
      JSON.stringify({ content: this.state.newMessageContent }))
      .then(response => response.json())
      .then((message: any) => {
        message['createdByUsername'] = this.props.user.username;
        message['createdAt'] = new Date(message['createdAt']);
        this.state.messages.push(message);
        this.setState({ newMessageContent: '' });
      });
  }

  render() {
    return <div className="MessageWindow">
      <div className="MessageList">
        {this.state.messages.map((message: IMessage) => {
          const date = message.createdAt;
          return <div className="MessageListEntry" key={message.id}>
            <span className="username">{message.createdByUsername}</span>
            <span className="created_at">{`${date.getMonth()+1}/${date.getDay()} ${date.getHours()}:${date.getMinutes()}`}</span>
            <br />
            <span className="content">{message.content}</span>
          </div>
        })}
      </div>
      <form className="AddMessage" onSubmit={this.handleSubmit}>
        <input
          type="text"
          value={this.state.newMessageContent}
          onChange={this.handleChange}
          placeholder="Enterで送信..."
        ></input>
      </form>
    </div>
  }

}

interface IProps {
  channel: IChannel;
  user: IUser;
}

interface IState {
  messages: Array<IMessage>;
  newMessageContent: string;
}

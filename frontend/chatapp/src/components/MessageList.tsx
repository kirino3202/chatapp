import React from "react";
import { IChannel } from "../model/IChannel";
import { IMessage } from "../model/IMessage";
import { callBackendAPI } from "../callBackendAPI";
import { IUser } from "../model/IUser";

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
        console.log(response);
        response.forEach((message: any) => message['createdAt'] = new Date(message['createdAT']));
        this.setState({
          messages: response
        })
      });
  }

  handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    console.log(event.target.value);
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
        this.state.messages.push(message);
        this.forceUpdate();
      });
  }

  render() {
    return <div>
      {this.state.messages.map((message: IMessage) => {
        return <div key={message.id}>
          <p>{message.createdByUsername}</p>
          <p>{message.content}</p>
        </div>
      })}
      <form onSubmit={this.handleSubmit}>
        <input type="text" value={this.state.newMessageContent} onChange={this.handleChange}></input>
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

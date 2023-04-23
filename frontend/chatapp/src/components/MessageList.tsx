import React from "react";
import { IChannel } from "../model/IChannel";
import { IMessage } from "../model/IMessage";
import { callBackendAPI } from "../callBackendAPI";
import { IUser } from "../model/IUser";
import './MessageList.css';

export class MessageList extends React.Component<IProps, IState> {
  updateTimeoutId: NodeJS.Timer | null;
  messageBottomElement: React.RefObject<HTMLDivElement>;
  shouldScrollToBottom: boolean;

  constructor(props: IProps) {
    super(props);

    this.state = {
      messages: [],
      newMessageContent: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleDelete = this.handleDelete.bind(this);
    this.updateMessages = this.updateMessages.bind(this);

    this.updateTimeoutId = null;
    this.messageBottomElement = React.createRef();
    this.shouldScrollToBottom = true;
  }

  componentDidMount() {
    const _this = this;
    this.updateTimeoutId = setTimeout(function loop() {
      _this.updateMessages();
      _this.updateTimeoutId = setTimeout(loop, 1000);
    });
  }

  componentWillUnmount() {
    this.updateTimeoutId && clearInterval(this.updateTimeoutId);
  }

  componentDidUpdate() {
    this.shouldScrollToBottom && this.messageBottomElement.current?.scrollIntoView({ behavior: "smooth" });
  }

  updateMessages() {
    callBackendAPI(`/api/channel/${this.props.channel.id}/message?after=${this.state.messages[this.state.messages.length - 1]?.id || 0}`, 'GET')
      .then(response => response.json())
      .then(response => {
        const rect = this.messageBottomElement.current?.getBoundingClientRect();
        this.shouldScrollToBottom = rect === undefined || (0 <= rect.bottom && rect.top <= window.innerHeight);

        response.forEach((message: any) => message['createdAt'] = new Date(message['createdAt']));
        this.setState({
          messages: this.state.messages.concat(response)
        })
      });
  }

  handleChange(event: React.ChangeEvent<HTMLInputElement>) {
    this.setState({ newMessageContent: event.target.value });
  }

  handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!this.state.newMessageContent.trim()) {
      return;
    }
    callBackendAPI(`/api/channel/${this.props.channel.id}/message`,
      'POST',
      JSON.stringify({ content: this.state.newMessageContent.trim() }))
      .then(() => {
        this.setState({ newMessageContent: '' });
      });
  }

  handleDelete(message: IMessage) {
    return () => {
      if (window.confirm('削除しますか？')) {
        callBackendAPI(`/api/channel/${this.props.channel.id}/message/${message.id}`, 'DELETE')
          .then(() => {
            this.setState({ messages: this.state.messages.filter(_message => _message.id !== message.id) });
          });
      }
    }
  }

  render() {
    return <div className="MessageWindow">
      <div className="MessageList">
        {this.state.messages.map((message: IMessage) => {
          const date = message.createdAt;
          return <div className="MessageListEntry" key={message.id}>
            <span className="username">{message.createdByUsername}</span>
            <span className="created_at">{`${date.getMonth() + 1}/${date.getDate()} ${date.getHours()}:${date.getMinutes()}`}</span>
            {(this.props.channel.createdBy === this.props.user.id
              || message.createdBy === this.props.user.id)
              && <span className="delete" onClick={this.handleDelete(message)}>削除</span>
            }
            <br />
            <span className="content">{message.content}</span>
          </div>
        })}
        <div ref={this.messageBottomElement}></div>
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

import React from "react";
import { IChannel } from "../model/IChannel";
import { ChannelList } from "./ChannelList";
import { MessageList } from "./MessageList";
import { IUser } from "../model/IUser";

export class Home extends React.Component<IProps, IState>{
  constructor(props: IProps) {
    super(props);

    this.state = {
      channel: null
    };

    this.selectChannelHandler = this.selectChannelHandler.bind(this);
  }

  selectChannelHandler(channel: IChannel) {
    return () => {
      this.setState({ channel: channel });
    }
  }

  render() {
    return <div>
      <ChannelList selectChannel={this.selectChannelHandler}></ChannelList>
      {this.state.channel &&
        <MessageList key={this.state.channel.id} channel={this.state.channel} user={this.props.user}></MessageList>}
    </div>
  }
}

interface IProps {
  user: IUser;
}

interface IState {
  channel: IChannel | null;
}

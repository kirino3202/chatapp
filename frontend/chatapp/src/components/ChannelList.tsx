import React from 'react';
import { IChannel } from '../model/IChannel';
import { callBackendAPI } from '../callBackendAPI';
import './ChannelList.css';

export class ChannelList extends React.Component<IProps, IState> {

  constructor(props: IProps) {
    super(props);
    this.state = {
      channels: []
    };

    this.addChannelHandler = this.addChannelHandler.bind(this);
  }

  componentDidMount() {
    fetch('/api/channel')
      .then(response => response.json())
      .then(response => {
        this.setState({ channels: response });
        if (response.length > 0) {
          this.props.selectChannel(response[0])();
        }
      });
  }

  addChannelHandler() {
    const name = prompt('チャンネル名を入力してください');
    if (name) {
      const body = {
        name: name
      };
      callBackendAPI('/api/channel', 'POST', JSON.stringify(body))
        .then(response => response.json())
        .then((response: IChannel) => {
          this.state.channels.push(response);
          this.forceUpdate();
        });

    }
  }

  render() {
    return !this.state.channels.length ? null :
      <div className="ChannelList">
        {this.state.channels.map(channel => {
          const channelClassName = 'ChannelListEntry '
            + (channel.id === this.props.selectedChannel?.id ? 'Selected' : '');
          return <div
            className={channelClassName}
            key={channel.id}
            onClick={this.props.selectChannel(channel)}
          >
            {channel.name}
          </div>;
        })}
        <div className="AddChannel" onClick={this.addChannelHandler}>
          ＋ テキストチャンネルを追加
        </div>
      </div>
      ;
  }
}

interface IProps {
  selectChannel: Function;
  selectedChannel: IChannel | null;
}

interface IState {
  channels: Array<IChannel>
}

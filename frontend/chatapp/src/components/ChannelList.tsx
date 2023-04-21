import React from 'react';
import { IChannel } from '../model/IChannel';
import { callBackendAPI } from '../callBackendAPI';

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
      <div>
        {this.state.channels.map(channel => {
          return <div key={channel.id} onClick={this.props.selectChannel(channel)}>
            <p>{channel.name}</p>
          </div>;
        })}
        <div onClick={this.addChannelHandler}>チャンネルを追加</div>
      </div>
      ;
  }
}

interface IProps {
  selectChannel: Function;
}

interface IState {
  channels: Array<IChannel>
}

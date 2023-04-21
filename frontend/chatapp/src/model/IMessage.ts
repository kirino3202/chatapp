export interface IMessage {
  id: number;
  channelId: number;
  content: string;
  createdBy: number;
  createdByUsername: string;
  createdAt: Date;
}

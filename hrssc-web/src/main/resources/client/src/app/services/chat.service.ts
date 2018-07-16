import { Injectable } from '@angular/core';
import * as stompjs from 'stompjs'
import * as SockJS from 'sockjs-client';
import { AuthenticateService } from './authenticate.service';

@Injectable()
export class ChatService {
  chatMessages=[];
  username: any;
  private stompClient;
  public serverWsUrl = "http://localhost:8080/ws";
  public topic = null;
  public currentSubscription = null;


  constructor(
    private auth: AuthenticateService
  ) { 
    this.username = this.auth.getUserName();
  }

  connect(roomId) {
    let ws = new SockJS(this.serverWsUrl);
    this.stompClient = stompjs.over(ws);
    let that = this;
    that.stompClient.connect({}, function () {
      that.topic = `/app/chat/${roomId}`;
      if (that.currentSubscription) {
        that.currentSubscription.unsubscribe();
      }
      that.currentSubscription = that.stompClient.subscribe(`/channel/${roomId}`, (message) => {
        if (message.body) {
          var messageReceived = JSON.parse(message.body);
          if (messageReceived.type == 'CHAT' && messageReceived.sender != that.username) {
            that.chatMessages.push({
              content: messageReceived.content,
              isSent: false,
              timeSent: that.getTimestamp()
            })
          }

        }
      });
      that.stompClient.send(`${that.topic}/addUser`,
        {},
        JSON.stringify({ sender: that.username, type: 'JOIN' })
      );
    });

  }

  getMessages() {
    return this.chatMessages;
  }

  sendMessage(msg) {
    const timestamp = this.getTimestamp();
    // const email = 
    this.chatMessages = this.getMessages();
    this.chatMessages.push({
      content: msg,
      timeSent: timestamp,
      isSent: true
      // email: 
    })

    if(msg && this.stompClient){
      var chatMessage = {
        sender: this.username,
        content: msg,
        type: 'CHAT'
      };
      this.stompClient.send(`${this.topic}/sendMessage`, {}, JSON.stringify(chatMessage));
    }
  }

  getTimestamp() {
    const now = new Date();
    const date = now.getUTCFullYear() + '/' +
      (now.getUTCMonth() + 1) + '/' +
      now.getUTCDate();
    const time = now.getUTCHours() + ':' +
      now.getUTCMinutes() + ':' +
      now.getUTCSeconds();
    return time;
  }

}

import { Injectable } from '@angular/core';
import * as stompjs from 'stompjs'
import * as SockJS from 'sockjs-client';
import { AuthenticateService } from './authenticate.service';
import { ContractService } from './contract.service';

@Injectable()
export class ChatService {
  chatMessages = [];
  username: any;
  userId: any;
  private stompClient;
  public serverWsUrl = "http://localhost:8080/ws";
  public topic = null;
  public currentSubscription = null;
  public contractId: any;


  constructor(
    private auth: AuthenticateService,
    private contractService: ContractService
  ) {
    this.username = this.auth.getUserName();
    this.userId = this.auth.getUserId();
  }

  connect(roomId) {
    this.contractId = roomId;

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
              timeSent: that.getTimestamp(),
              userSent: messageReceived.sender
            })
          }

        }
      });
      that.stompClient.send(`${that.topic}/addUser`,
        {},
        JSON.stringify({ sender: that.username, type: 'JOIN', contractId: roomId, userId: that.userId })
      );
    });

  }

  getMessages() {
    return this.chatMessages;
  }

  getLogChatById(contractId) {
    this.contractService.getLogChatById(contractId).subscribe(
      res => {
        res.forEach(el => {
          this.chatMessages.push({
            content: el.messageContent,
            timeSent: el.sentTime,
            userSent: el.userByUserId.username
            // email: 
          })
        });
      },
      err => {

      }
    );
  }

  sendMessage(msg) {
    const timestamp = this.getTimestamp();
    // const email = 
    this.chatMessages = this.getMessages();
    this.chatMessages.push({
      content: msg,
      timeSent: timestamp,
      userSent: this.username
      // email: 
    })

    if (msg && this.stompClient) {
      var chatMessage = {
        sender: this.username,
        content: msg,
        type: 'CHAT',
        contractId: this.contractId,
        userId: this.userId
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

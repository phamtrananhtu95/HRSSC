import { Injectable } from '@angular/core';
import * as stompjs from 'stompjs'
import * as SockJS from 'sockjs-client';
import { AuthenticateService } from './authenticate.service';
import { ContractService } from './contract.service';
import { NotificationService } from './notification.service';

@Injectable()
export class ChatService {
  chatMessages = [];
  logNotify = [];
  username: any;
  userId: any;
  private stompClient;
  private stompClientNoti;
  public serverWsUrl = "http://localhost:8080/ws";
  public topic = null;
  public currentSubscription = null;
  public currentSubNoti = null;
  public contractId: any;


  constructor(
    private auth: AuthenticateService,
    private contractService: ContractService,
    private notifyService: NotificationService
  ) {
    this.username = this.auth.getUserName();
    this.userId = this.auth.getUserId();
  }

  connect(roomId) {
    this.contractId = roomId;
    let username = this.auth.getUserName();
    let userId = this.auth.getUserId();
    let ws = new SockJS(this.serverWsUrl);
    this.stompClient = stompjs.over(ws);
    // this.stompClient.debug = null;
    let that = this;
    that.stompClient.connect({}, function () {
      that.topic = `/app/chat/${roomId}`;
      if (that.currentSubscription) {
        that.currentSubscription.unsubscribe();
      }
      that.currentSubscription = that.stompClient.subscribe(`/channel/${roomId}`, (message) => {
        if (message.body) {
          var messageReceived = JSON.parse(message.body);
          if (messageReceived.type == 'CHAT' && messageReceived.sender != username) {
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
        JSON.stringify({ sender: username, type: 'JOIN', contractId: roomId, userId: userId })
      );
    });

  }

  connectNotifyChannel(roomId, username) {

    let ws = new SockJS(this.serverWsUrl);
    this.stompClientNoti = stompjs.over(ws);
    // this.stompClient.debug = null;
    let that = this;
    console.log(username);
    that.stompClientNoti.connect({}, function () {
      that.topic = `/app/notification/${roomId}`;
      // if (that.currentSubNoti) {
      //   that.currentSubNoti.unsubscribe();
      // }
      that.currentSubNoti = that.stompClientNoti.subscribe(`/channel/${roomId}`, (message) => {
        if (message.body) {
          var messageReceived = JSON.parse(message.body);
          if (messageReceived.type == 'CHAT' && messageReceived.sender != username) {
            that.logNotify.push({
              content: messageReceived.content,
              timeSent: that.getTimestamp(),
              notiType: messageReceived.notiType,
              projectId: messageReceived.projectId,
              resourceId: messageReceived.resourceId,
              userId: messageReceived.userId
            })
          }

        }
      });
      that.stompClientNoti.send(`${that.topic}/addUser`,
        {},
        JSON.stringify({ sender: username, type: 'JOIN' })
      );
    });
  }
  sendNotify(msg, notiType, projectId, resourceId, userId) {

    // const timestamp = this.getTimestamp();
    // const email = 
    // this.logNotify = this.getLogNotify();
    // this.logNotify.push({
    //   content: msg,
    //   timeSent: timestamp,
    //   // userSent: this.username,
    //   // sendTo: sendTo,
    //   notiType: notiType,
    //   projectId: projectId,
    //   resourceId: resourceId,
    //   userId: userId

    //   // email: 
    // })



    if (msg && this.stompClientNoti) {
      var notifyMessage = {
        sender: this.username,
        content: msg,
        type: 'CHAT',
        // sendTo: sendTo,
        notiType: notiType,
        projectId: projectId,
        resourceId: resourceId,
        userId: userId

      };
      this.stompClientNoti.send(`${this.topic}/sendNotification`, {}, JSON.stringify(notifyMessage));
    }
  }

  getLogNotifyByReceiveId(userId) {
    this.logNotify = [];
    this.notifyService.getLogNotify(userId).subscribe(
      res => {
        res.forEach(el => {
          this.logNotify.push({
            content: el.content,
            timeSent: el.date,
            // sendTo: el.userByUserId.username,
            notiType: el.type,
            projectId: el.projectId,
            resourceId: el.humanResourceId,
            userId: el.userId
          })
        });
      },
      err => {

      }
    );
  }

  getLogNotify() {
    return this.logNotify;
  }

  getMessages() {
    return this.chatMessages;
  }

  getLogChatById(contractId) {
    this.chatMessages = [];
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
    let username = this.auth.getUserName();
    let userId = this.auth.getUserId();
    const timestamp = this.getTimestamp();
    // const email = 
    this.chatMessages = this.getMessages();
    this.chatMessages.push({
      content: msg,
      timeSent: timestamp,
      userSent: username
      // email: 
    })

    if (msg && this.stompClient) {
      var chatMessage = {
        sender: username,
        content: msg,
        type: 'CHAT',
        contractId: this.contractId,
        userId: userId
      };
      this.stompClient.send(`${this.topic}/sendMessage`, {}, JSON.stringify(chatMessage));
    }
  }

  getTimestamp() {
    const now = new Date();
    const date = now.getUTCFullYear() + '/' +
      (now.getUTCMonth() + 1) + '/' +
      now.getUTCDate();
    const time = now.getHours() + ':' +
      now.getMinutes() + ':' +
      now.getSeconds();
    return time;
  }

}

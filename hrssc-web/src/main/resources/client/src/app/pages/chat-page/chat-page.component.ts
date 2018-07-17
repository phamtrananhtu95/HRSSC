import { Component, OnInit } from '@angular/core';
import * as stompjs from 'stompjs'
import * as SockJS from 'sockjs-client';
import { LocalstoragesService } from '../../services/localstorages.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ChatService } from '../../services/chat.service';
declare var $: any;

@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.css']
})
export class ChatPageComponent implements OnInit {
  private serverUrl = "http://localhost:8080/ws";
  private stompClient;
  public username = null;
  public roomId = null;
  public topic = null;
  public currentSubscription = null;

  // 
  public messageInput;
  public listMessage = [];
  public listMessageReceive = [];
  // 

  constructor(
    private localService: LocalstoragesService,
    private auth: AuthenticateService,
    private chatService: ChatService
  ) { }

  ngOnInit() {
    if (this.auth.checkLogin()) {
      this.username = this.auth.getUserName();
      // this.connect();
      this.chatService.connect(2310);
      this.listMessage = this.chatService.getMessages();
    }
  }
  getListMessage() {
    this.listMessage = this.chatService.getMessages();
  }

  showChatPopup() {
    $('#qnimate').addClass('popup-box-on');
  }

  
}

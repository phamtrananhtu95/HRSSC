import { Component, OnInit } from '@angular/core';
import * as stompjs from 'stompjs'
import * as SockJS from 'sockjs-client';
import { LocalstoragesService } from '../../services/localstorages.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ChatService } from '../../services/chat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ContractService } from '../../services/contract.service';
declare var $: any;

@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.css']
})
export class ChatPageComponent implements OnInit {
  // private serverUrl = "http://localhost:8080/ws";
  // private stompClient;
  public username = null;
  public roomId = null;
  public topic = null;
  public currentSubscription = null;

  // 
  public messageInput;
  public listMessage = [];
  public listMessageReceive = [];
  // 

  public contractId: number;

  constructor(
    private localService: LocalstoragesService,
    private auth: AuthenticateService,
    private chatService: ChatService,
    private route: ActivatedRoute,
    private router: Router,
    private contractService: ContractService
  ) { 
    this.route.queryParams.subscribe(param => {
      this.contractId= this.route.snapshot.queryParams['id'];
      this.getLogChatContract(this.contractId);
    });
  }

  ngOnInit() {
    // if (this.auth.checkLogin()) {
    //   this.username = this.auth.getUserName();
    //   // this.connect();
    //   // this.chatService.connect(2310);
    //   // this.listMessage = this.chatService.getMessages();
    // }
  }

  getLogChatContract(id) {
     this.listMessage = [];
     this.contractService.getLogChatById(id).subscribe(
       res => {
        res.forEach(el => {
          this.listMessage.push({
            content: el.messageContent,
            timeSent: el.sentTime,
            userSent: el.userByUserId.username
            // email: 
          })
        });
        console.log(this.listMessage);
        
       },
       err => {

       }
     );
  }
  getListMessage() {
    // this.listMessage = this.chatService.getMessages();
  }

  showChatPopup() {
    $('#qnimate').addClass('popup-box-on');
  }

  
}

import { Component, OnInit, Input } from '@angular/core';
import { AuthenticateService } from '../../../services/authenticate.service';

@Component({
  selector: 'app-chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.css']
})
export class ChatMessageComponent implements OnInit {
  @Input() chatMessage;
  userEmail: string;
  messageContent: string;
  userSent: string;
  time: Date = new Date();

  constructor(
    private auth: AuthenticateService
  ) { }

  ngOnInit(chatMessage = this.chatMessage) {
    

    this.userEmail = this.auth.getUserName();
    this.messageContent = chatMessage.content;
    this.time = chatMessage.timeSent;
    this.userSent = chatMessage.userSent;
    // this.isSent = chatMessage.isSent;


  }


}

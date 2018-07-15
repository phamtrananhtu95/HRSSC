import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.css']
})
export class ChatMessageComponent implements OnInit {
  @Input() chatMessage: any;
  userEmail: string;
  messageContent: string;
  time: Date = new Date();
  // isOwnMessage: boolean;

  constructor() { }

  ngOnInit(chatMessage = this.chatMessage) {
    this.userEmail = chatMessage.userEmail;
    this.messageContent = chatMessage.content;
    this.time = chatMessage.timeSent;
  }


}

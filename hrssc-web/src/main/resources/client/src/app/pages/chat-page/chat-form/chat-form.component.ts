import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../../services/chat.service';
declare var $: any;

@Component({
  selector: 'app-chat-form',
  templateUrl: './chat-form.component.html',
  styleUrls: ['./chat-form.component.css']
})
export class ChatFormComponent implements OnInit {
  messageInput: string;

  constructor(private chatService: ChatService) { }

  ngOnInit() {
  }

  send() {
    $( "#messageInput" ).blur();
    this.chatService.sendMessage(this.messageInput);
    this.messageInput = '';
  }

  handleSubmit(event) {
    if(event.keyCode === 13) {
      this.send();
      $( "#messageInput" ).blur();
    }
  }

}

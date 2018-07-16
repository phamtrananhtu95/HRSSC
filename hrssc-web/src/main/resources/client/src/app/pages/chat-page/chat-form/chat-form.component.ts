import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ChatService } from '../../../services/chat.service';
declare var $: any;

@Component({
  selector: 'app-chat-form',
  templateUrl: './chat-form.component.html',
  styleUrls: ['./chat-form.component.css']
})
export class ChatFormComponent implements OnInit {
  messageInput: string;
  @Output() reloadListMessage: EventEmitter<any> = new EventEmitter();

  constructor(private chatService: ChatService) { }
  

  ngOnInit() {
  }

  send() {
    // $( "#messageInput" ).blur();
    this.chatService.sendMessage(this.messageInput);
    this.reloadListMessage.emit();
    this.messageInput = '';
  }

  handleSubmit(event) {
    if(event.keyCode === 13) {
      this.send();
      this.reloadListMessage.emit();
      $( "#messageInput" ).blur();
    }
  }

}

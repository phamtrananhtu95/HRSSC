import { Component, OnInit, Input, ViewChild, ElementRef, OnChanges, AfterViewChecked } from '@angular/core';
import { ChatService } from '../../../services/chat.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ContractService } from '../../../services/contract.service';
declare var $: any;

@Component({
  selector: 'app-chat-popup',
  templateUrl: './chat-popup.component.html',
  styleUrls: ['./chat-popup.component.css']
})
export class ChatPopupComponent implements OnInit, OnChanges, AfterViewChecked {
  @Input() contractid: any;
  @ViewChild('scroller2') private feedContainer: ElementRef;
  feed: any;
  interval;
  public roomId;
  messageInput: string;
  public username;

  constructor(
    private chatService: ChatService,
    private auth: AuthenticateService,
    private contractService: ContractService
  ) { }

  ngOnInit() {
    this.feed = this.chatService.getMessages();
    this.interval = setInterval(() => {
      this.feed = this.chatService.getMessages();
    }, 1000);

    this.username = this.auth.getUserName();

  }
  ngOnChanges() {
    if (this.contractid) {
      this.roomId = this.contractid
      this.chatService.connect(this.roomId);
      this.chatService.getLogChatById(this.roomId);
    }
  }
  send() {
    // $( "#messageInput" ).blur();
    this.chatService.sendMessage(this.messageInput);
    this.messageInput = '';
  }

  handleSubmit(event) {
    if (event.keyCode === 13) {
      this.send();
    }
  }

  scrollToBottom(): void {
    this.feedContainer.nativeElement.scrollTop = this.feedContainer.nativeElement.scrollHeight;
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  removeChatBox() {
    $('#qnimate').removeClass('popup-box-on');
  }

}

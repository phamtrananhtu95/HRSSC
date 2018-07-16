import { Component, OnInit, OnChanges, ViewChild, ElementRef, AfterViewChecked, Input } from '@angular/core';
import { ChatService } from '../../../services/chat.service';

@Component({
  selector: 'app-chat-feed',
  templateUrl: './chat-feed.component.html',
  styleUrls: ['./chat-feed.component.css']
})
export class ChatFeedComponent implements OnInit, OnChanges, AfterViewChecked {
  @Input() feed;
  @ViewChild('scroller') private feedContainer: ElementRef;
  interval: any;

  constructor(
    private chatService: ChatService
  ) {
  }

  ngOnInit() {
    this.feed = this.chatService.getMessages();
    this.interval = setInterval(() => { 
      this.feed = this.chatService.getMessages(); 
  }, 1000);
  }

  
  ngOnChanges() {
    this.feed = this.chatService.getMessages();
    console.log(this.feed);
  }

  scrollToBottom(): void {
    this.feedContainer.nativeElement.scrollTop = this.feedContainer.nativeElement.scrollHeight;
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }
}

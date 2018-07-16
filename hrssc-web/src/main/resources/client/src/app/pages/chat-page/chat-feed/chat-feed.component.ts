import { Component, OnInit, OnChanges, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { ChatService } from '../../../services/chat.service';

@Component({
  selector: 'app-chat-feed',
  templateUrl: './chat-feed.component.html',
  styleUrls: ['./chat-feed.component.css']
})
export class ChatFeedComponent implements OnInit, OnChanges, AfterViewChecked {
  @ViewChild('scroller') private feedContainer: ElementRef;
  feed: any;


  constructor(
    private chatService: ChatService
  ) { }

  ngOnInit() {
    this.feed = this.chatService.getMessages();
    
  }

  ngOnChanges(){
    this.feed = Object.assign({}, this.chatService.getMessages());
    console.log(this.feed);
  }

  scrollToBottom(): void {
    this.feedContainer.nativeElement.scrollTop = this.feedContainer.nativeElement.scrollHeight;
  }

  ngAfterViewChecked() {
    this.scrollToBottom();    
  }
}

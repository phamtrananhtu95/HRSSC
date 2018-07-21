import { Component, OnInit, Injectable } from '@angular/core';
import { HeaderService } from './header.component.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Observable } from 'rxjs';
import { ChatService } from '../../services/chat.service';

@Injectable()
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private userName: any;
  public listLogNotify = [];

  constructor(
    public header: HeaderService,
    public authenticateService: AuthenticateService,
    private chatService: ChatService
  ) { }

  ngOnInit() {
    this.header.userInfo.subscribe(userName => {
      this.userName = userName;
    });

    // load case!
    let userName = this.authenticateService.getUserName();
    if(userName){
      this.header.setUserNametoHead(userName);
    }

    // this.chatService.connectNotifyChannel(2310);
    // this.listLogNotify = this.chatService.getLogNotify();
    
  }



}

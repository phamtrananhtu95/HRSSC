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
  public userId: any;
  public listLogNotify = [];
  public countNotify: number;
  public interval;
  constructor(
    public header: HeaderService,
    public authenticateService: AuthenticateService,
    private chatService: ChatService
  ) { }

  ngOnInit() {
    console.log(this.header.userInfo);
    
    this.header.userInfo.subscribe(userName => {
      this.userName = userName;
      if(this.userName != ""){
        this.chatService.connectNotifyChannel("notifyRoom", userName);
      }
      
    });
    this.header.userInfoId.subscribe(userId => {
      this.userId = userId;
      if(this.userId != ""){
        this.chatService.getLogNotifyByReceiveId(userId);
      }
    })
    // this.userId = this.authenticateService.getUserId();
    // this.chatService.getLogNotifyByReceiveId(this.userId);
    // load case!
    let userName = this.authenticateService.getUserName();
    if(userName){
      this.header.setUserNametoHead(userName);
    }

    let userId = this.authenticateService.getUserId();
    if(userId){
      this.header.setUserIdToHead(userId);
    }
    

    this.interval = setInterval(() => {
      this.listLogNotify = this.chatService.getLogNotify();
      
      this.countNotify = this.listLogNotify.length;
    }, 3000);

    
    
   
    
  }
  ngOnChanges() {
    this.listLogNotify = this.chatService.getLogNotify();
    this.countNotify = this.listLogNotify.length;
    console.log(this.countNotify);
  }




}

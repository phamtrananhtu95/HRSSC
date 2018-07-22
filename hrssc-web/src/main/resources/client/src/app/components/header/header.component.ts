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
  public countNotify: number;
  public interval;
  constructor(
    public header: HeaderService,
    public authenticateService: AuthenticateService,
    private chatService: ChatService
  ) { }

  ngOnInit() {
    this.header.userInfo.subscribe(userName => {
      this.userName = userName;
      if(this.userName != ""){
        this.chatService.connectNotifyChannel("notifyRoom", userName);
      }
      
    });

    // load case!
    let userName = this.authenticateService.getUserName();
    if(userName){
      this.header.setUserNametoHead(userName);
      
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

import { Component, OnInit, Injectable } from '@angular/core';
import { HeaderService } from './header.component.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Observable } from 'rxjs';

@Injectable()
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private userName: any;

  constructor(
    public header: HeaderService,
    public authenticateService: AuthenticateService
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
    
  }

}

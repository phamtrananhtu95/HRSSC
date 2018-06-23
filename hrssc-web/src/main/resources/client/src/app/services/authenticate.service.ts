import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { SessionsService } from './sessions.service';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';
import { HeaderService } from '../components/header/header.component.service';

@Injectable()
export class AuthenticateService {

  public USER_INFOR = "user-info";
  public HAS_LOGIN = "has-login";
  public username = "";
  constructor(
    private session: SessionsService,
    private router: Router,
    public header: HeaderService
  ) { }


  setLogin(value: boolean){
    console.log(value);
    this.session.saveInLocal(this.HAS_LOGIN, value);
  }

  checkLogin() {
    let login = this.session.getFromLocal(this.HAS_LOGIN);
    if (login) {
      return true;
    }
    this.router.navigate(["login"]);
    return false;
  }

  saveUserInfo(userInfo: any){
    let username = userInfo ? userInfo.username : null;
    this.header.setUserNametoHead(username);
    this.session.saveInLocal(this.USER_INFOR, userInfo);
  }

  getUsetInfo(){
    return this.session.getFromLocal(this.USER_INFOR);
  }

  getUserName(){
    let userInfo = this.getUsetInfo();   
    return userInfo ? userInfo.username : null;
  }

  getUserId(){
    let userInfo = this.getUsetInfo();
    return userInfo ? userInfo.id : null;
  }

}

import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { SessionsService } from './sessions.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HeaderService } from '../components/header/header.component.service';

@Injectable()
export class AuthenticateService {

  public USER_INFOR = "user-info";
  public HAS_LOGIN = "has-login";
  public username = "";

  // Authen UI
  public roleUI = {
    1: ["manageCompany"],
    2: ["manageResources", "manageProject", "feedBack", "manageManager", "manageCompanyInfo", "manageContract"],
    3: ["manageResources", "manageProject", "feedBack", "manageContract"]
  };

  constructor(
    private session: SessionsService,
    private router: Router,
    public header: HeaderService
  ) { }


  setLogin(value: boolean) {
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

  saveUserInfo(userInfo: any) {
    let username = userInfo ? userInfo.username : null;
    let userId = userInfo ? userInfo.id : null;
    this.header.setUserNametoHead(username);
    this.header.setUserIdToHead(userId);
    this.session.saveInLocal(this.USER_INFOR, userInfo);
  }

  getUserInfo() {
    return this.session.getFromLocal(this.USER_INFOR);
  }

  getUserName() {
    let userInfo = this.getUserInfo();
    return userInfo ? userInfo.username : null;
  }

  getUserId() {
    let userInfo = this.getUserInfo();
    return userInfo ? userInfo.id : null;
  }

  getCompanyId() {
    let userInfo = this.getUserInfo();
    return userInfo ? userInfo.companyID : null;
  }

  getRoleId() {
    let userInfo = this.getUserInfo();
    return userInfo ? userInfo.roleID : null;
  }

  checkRole(subMenu) {
    let roleID =this.getRoleId();
    if (subMenu && roleID) {
      let listSubMenu = this.roleUI[roleID];
      if (listSubMenu && listSubMenu.length > 0) {
        return listSubMenu.indexOf(subMenu) > -1;
      }
    }
    return false;
  }


}

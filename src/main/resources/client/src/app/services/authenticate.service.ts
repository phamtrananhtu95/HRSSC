import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { SessionsService } from './sessions.service';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';

@Injectable()
export class AuthenticateService {

  public HAS_LOGIN = "has-login";
  constructor(
    private session: SessionsService,
    private router: Router
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


}

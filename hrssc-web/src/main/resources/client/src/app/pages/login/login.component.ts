import { Component, OnInit } from '@angular/core';
import { MenuLeftService } from '../../components/menu-left/menu-left.component.service';
import { HeaderService } from '../../components/header/header.component.service';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { User } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import {Observable} from 'rxjs/Rx';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';
  public username: string;
  public password: string;

  constructor(
    public menu: MenuLeftService,
    public header: HeaderService,
    private loginService: LoginService,
    private router: Router,
    private authenticate: AuthenticateService
  ) { }

  ngOnInit() {
    this.menu.hideMenu(true);
    this.header.hideHeader(true);
    this.authenticate.setLogin(false);
  }

  ngOnDestroy() {
    this.menu.hideMenu(false);
    this.header.hideHeader(false);
  }

  login() {
    this.loading = true;
    let body = {
      username: this.username,
      password: this.password
    }
    this.loginService.login(body)
      .subscribe(res => {
        if (res && res.username) {
          // login successful
          
          this.authenticate.saveUserInfo(res);
          this.authenticate.setLogin(true);
          // Save user info authen
          if (res.firstLogin == true) {
            this.router.navigate(['chooseDomain']);
          }
          else { this.router.navigate(['home']); }
        } else {
          // login failed
          this.error = 'Username or password is incorrect';
          this.loading = false;
        }
      }, error => {
        this.loading = false;
        this.error = 'Username or password is incorrect';
      });
  }
}

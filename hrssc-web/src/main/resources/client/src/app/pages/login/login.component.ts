import { Component, OnInit } from '@angular/core';
import { MenuLeftService } from '../../components/menu-left/menu-left.component.service';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { User } from '../../models';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';
  public user: User = new User();

  constructor(
    public menu: MenuLeftService,
    private loginService: LoginService,
    private router: Router) { }

  ngOnInit() {
    this.menu.hideMenu(true);
  }

  ngOnDestroy() {
    this.menu.hideMenu(false);
  }

  login() {
    this.loading = true;
    this.loginService.login(this.user)
      .subscribe(result => {
        if (result) {
          // login successful
          this.router.navigate(['home']);
        } else {
          // login failed
          this.error = 'Username or password is incorrect';
          this.loading = false;
        }
      }, error => {
        this.loading = false;
        this.error = error;
      });
  }
}

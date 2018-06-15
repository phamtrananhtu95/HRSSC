import { Component, OnInit } from '@angular/core';
import { MenuLeftService } from '../../components/menu-left/menu-left.component.service';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { User } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';
  user: User = new User();

  constructor(
    public menu: MenuLeftService,
    private loginService: LoginService,
    private router: Router,
    private authenticate: AuthenticateService
  ) { }

  ngOnInit() {
    this.menu.hideMenu(true);
    this.authenticate.setLogin(false);
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
          this.authenticate.setLogin(true);
          this.router.navigate(['/home']);
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

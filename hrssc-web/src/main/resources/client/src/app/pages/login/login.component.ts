import { Component, OnInit } from '@angular/core';
import { MenuLeftService } from '../../components/menu-left/menu-left.component.service';
import { Router } from '@angular/router';
import { User } from '../../models';
import { LoginService } from '../../services/login.service';

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
  errorMessage:string;

  constructor(
    public menu: MenuLeftService,
    private loginService: LoginService,
    private router: Router,
  ) { }

  ngOnInit() {
    this.menu.hideMenu(true);
  }

  ngOnDestroy() {
    this.menu.hideMenu(false);
  }

  login() {
    this.loading = true;
    console.log(this.user);
    this.loginService.login(this.user)
      .subscribe(result => {
          // login successful
          this.router.navigate(['/home']);
         
      }, error => {
         // login failed
         this.error = 'Username or password is incorrect';
         this.loading = false;
      });
  }
}

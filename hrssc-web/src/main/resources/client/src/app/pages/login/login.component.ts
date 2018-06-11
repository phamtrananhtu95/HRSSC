import { Component, OnInit } from '@angular/core';
import { MenuLeftService } from '../../components/menu-left/menu-left.component.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(public menu: MenuLeftService) { }

  ngOnInit() {
    this.menu.hideMenu(true);
  }

  ngOnDestroy (){
    this.menu.hideMenu(false);
  }
}

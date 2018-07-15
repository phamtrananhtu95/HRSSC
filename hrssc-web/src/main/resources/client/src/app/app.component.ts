import { Component, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import 'rxjs/add/operator/map';
import { HttpClient } from '@angular/common/http';
import { MenuLeftService } from './components/menu-left/menu-left.component.service';
import { HeaderService } from './components/header/header.component.service';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public user: any;
  subscription: any;
  hideMenu: boolean;
  hideHeader: boolean;
  constructor(
    private http: Http,
    public menu: MenuLeftService,
    public header: HeaderService
  ) {
  }

  ngOnInit() {
    this.menu.currentVisible.subscribe(hideMenu => {
      console.log("Hide menu = " + hideMenu);
      this.hideMenu = hideMenu;
    });
    this.header.currentVisible.subscribe(hideHeader => {
      console.log("Hide header = " + hideHeader);
      this.hideHeader = hideHeader;
    });
  }
}

import { Component, OnInit, Injectable } from '@angular/core';
import { MenuLeftService } from './menu-left.component.service';

@Injectable()
@Component({
  selector: 'app-menu-left',
  templateUrl: './menu-left.component.html',
  styleUrls: ['./menu-left.component.css'],
  providers:[MenuLeftService]
})
export class MenuLeftComponent implements OnInit {
  constructor(menu: MenuLeftService) {
   }

   ngOnInit() {
  }

}

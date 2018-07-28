import { Component, OnInit, Injectable } from '@angular/core';
import { MenuLeftService } from './menu-left.component.service';
import { AuthenticateService } from '../../services/authenticate.service';

@Injectable()
@Component({
  selector: 'app-menu-left',
  templateUrl: './menu-left.component.html',
  styleUrls: ['./menu-left.component.css'],
  providers:[MenuLeftService]
})
export class MenuLeftComponent implements OnInit {
  constructor(
    menu: MenuLeftService,
    private authenticateService: AuthenticateService
  ) {
   }

   ngOnInit() {
    (<any>window).appJsLoad = true;
  }

  checkRoleUI(subMenu){
    return this.authenticateService.checkRole(subMenu);
  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-manage-company',
  templateUrl: './admin-manage-company.component.html',
  styleUrls: ['./admin-manage-company.component.css']
})
export class AdminManageCompanyComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    // (<any>window).flagReloadPage = true;
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;
  }

}

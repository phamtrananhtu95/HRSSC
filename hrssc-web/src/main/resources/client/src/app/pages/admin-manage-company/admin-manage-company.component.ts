import { Component, OnInit } from '@angular/core';
import { CompaniesService } from '../../services/companies.service';
import { Router } from '@angular/router';
declare var $: any;
@Component({
  selector: 'app-admin-manage-company',
  templateUrl: './admin-manage-company.component.html',
  styleUrls: ['./admin-manage-company.component.css']
})
export class AdminManageCompanyComponent implements OnInit {

  public parentTitle = "Home";
  public title = " - Manage company";
  public subTitle = " - Companies";
  public titleLink = " / Companies";
  // =============================
  public companies;

  constructor(
    private companyService: CompaniesService,
    private router: Router
  ) { }

  ngOnInit() {
    // (<any>window).flagReloadPage = true;
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;
    this.getCompany();
  }

  getCompany() {
    this.companyService.getListCompany().subscribe(
      res => {
        this.companies = [];
        this.companies = res;
      },
      err => {

      }
    );
  }
  viewCompanyDetail(id){
    this.router.navigate(['company/info'], {queryParams:{"id": id}});
  }
}

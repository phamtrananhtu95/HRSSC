import { Component, OnInit } from '@angular/core';
import { CompaniesService } from '../../services/companies.service';
import { Register } from '../../models/register.model';

@Component({
  selector: 'app-admin-manage-account-request',
  templateUrl: './admin-manage-account-request.component.html',
  styleUrls: ['./admin-manage-account-request.component.css']
})
export class AdminManageAccountRequestComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Manage Company";
  public subTitle = " - Manage request";
  public titleLink = " / Manage request";

  // ================
  companies: Register[] = [];
  constructor(
    private companiesService: CompaniesService
  ) { }

  ngOnInit() {
    this.companiesService.getCompanies()
      .subscribe(
        res => {
          this.companies = res;
        },
        err => {
          console.log(err);
        });
  }

  acceptCompany(companyId) {
    this.companiesService.acceptCompany(companyId)
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log(err);
        });
  }

}

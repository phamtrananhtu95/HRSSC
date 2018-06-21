import { Component, OnInit } from '@angular/core';
import { CompaniesService } from '../../services/companies.service';
import { Register } from '../../models/register.model';
import { RestService } from '../../services/rest.service';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-admin-manage-account-request',
  templateUrl: './admin-manage-account-request.component.html',
  styleUrls: ['./admin-manage-account-request.component.css']
})
export class AdminManageAccountRequestComponent implements OnInit {
  companies : Register[] = [];
  constructor(
    private companiesService : CompaniesService,
    private restService : RestService,
    private employeeService: EmployeeService
  ) { }

  ngOnInit() {
    this.companiesService.getCompanies()
    .subscribe(
      res => {
        this.companies = res;
      },
      err=>{
        console.log(err);
      });
  }
  test(){
    this.employeeService.test().subscribe(
      res => {
        alert("zo");
      },
      err=>{
        alert("deo zo");
      });
  }
}

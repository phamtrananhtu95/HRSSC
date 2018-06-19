import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { Project, projectList, companyList, Company, Employee } from '../../models';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';

@Component({
  selector: 'hrssc-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public users: User[];
  public projects: Project[];
  public companies: Company[];
  public employees: Employee[];
  
  public parentTitle = "Home";

  constructor(
    private router: Router,
    private employeeService: EmployeeService,
    private authenticate: AuthenticateService,
  ) { }

  ngOnInit() {
    if (this.authenticate.checkLogin()) {
      this.projects = new projectList().projects;
      this.companies = new companyList().companies;
      // this.employeeService.getEmployees().subscribe(res => {
      // this.employees = res;
      // });
    }
  }

  viewResourceDetail(user: User) {
    this.router.navigate(['manager/resource/info']);
  }

  viewProjectDetail(project: Project) {
    this.router.navigate(['manager/project/info']);
  }

  viewCompanyDetail(company: Company) {
    this.router.navigate(['company/info']);
  }

}

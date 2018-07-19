import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { Project, Company, Employee } from '../../models';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';
import { CompaniesService } from '../../services/companies.service';

@Component({
  selector: 'hrssc-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public listLocation = [];

  public users: User[];
  // public projects: Project[];
  // public companies: Company[];
  public employees: Employee[];
  public resources = [];
  public projects = [];
  public companies = [];


  public parentTitle = "Home";

  public userId;

  constructor(
    private router: Router,
    private employeeService: EmployeeService,
    private prjSerivce: ProjectService,
    private authenticate: AuthenticateService,
    private companyService: CompaniesService
  ) { }

  ngOnInit() {
    if (this.authenticate.checkLogin()) {
      this.userId = this.authenticate.getUserId();
      this.getHumanResource();
      this.getCompany();
        this.getProjects();
    }

  }

  getHumanResource() {
    this.resources  =[];
    this.employeeService.getResource(this.userId).subscribe(
      res => {
        this.resources = res;
      },
      err => {
        console.log(err);
      });
  }
  getProjects() {
    this.projects = [];
    this.prjSerivce.getProjects(this.userId).subscribe(
      res => {
          this.projects = res;
          console.log(this.projects);
      },
      err => {

      }
    );
  }
  getCompany() {
    this.companies = [];
    this.companyService.getCompanyByUserId(this.userId).subscribe(
      res => {
        this.companies = res;
      },
      err => {

      }
    );
  }

  viewResourceDetail(humanResourceId) {
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": humanResourceId}});
  }

  viewProjectDetail(projectId) {
    this.router.navigate(['manager/project/info'], {queryParams:{"id": projectId}});
  }

  viewCompanyDetail(companyId) {
    this.router.navigate(['company/info'], {queryParams:{"id": companyId}});
  }

}

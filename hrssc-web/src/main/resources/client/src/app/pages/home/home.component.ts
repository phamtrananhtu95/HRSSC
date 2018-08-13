import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { Project, Company, Employee } from '../../models';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';
import { CompaniesService } from '../../services/companies.service';
import { Search } from '../../models/search.model';

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
  public listSkillOpt = [];
  public listLocationOpt = [
    {
      value: "Ho Chi Minh",
      label: "Ho Chi Minh"
    },
    {
      value: "Ha Noi",
      label: "Ha Noi"
    },
    {
      value: "Da Nang",
      label: "Da Nang"
    },
  ];
  public searchModel = new Search();
  public searchProjectModel = new Search();


  public parentTitle = "Home";

  public userId;

  public listSkillProject = [];
  public skillList: any;

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
      this.getSkillList();
    }

  }

  getHumanResource() {
    this.resources  =[];
    this.employeeService.getResource(this.userId).subscribe(
      res => {
        this.resources = res;
        this.resources.forEach(element => {
          element.availableDate = this.ConvertToDatetime(element.availableDate);
          element.availableDuration = this.ConvertToDatetime(element.availableDuration);
        });
        
      },
      err => {
        console.log(err);
      });
  }
  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    return dateParse;
  }
  getProjects() {
    this.projects = [];
    this.prjSerivce.getProjects(this.userId).subscribe(
      res => {
          this.projects = res;
          this.listSkillProject = [];
          this.projects.forEach(element => {
            element.createDate = this.ConvertToDatetime(element.createDate);
            element.endDate = this.ConvertToDatetime(element.endDate);

            // add skill string vao list
            this.skillList = [];
            element.skill = [];
            element.projectRequirementsById.forEach(el => {
              
              el.skillRequirementsById.forEach(el2 => {
                element.skill.push(el2.skillBySkillId.title)
              });
            });
          });
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
        console.log(this.companies);
        
      },
      err => {

      }
    );
  }
  getSkillList(){
    this.employeeService.getSkills().subscribe(
      res => {
        this.listSkillOpt = [];
        res.forEach(el => {
          this.listSkillOpt.push({ value: el.title, label: el.title });
        });

      }
    );
  }
  
  search() {
    this.searchModel.userId = this.userId;
    console.log(this.searchModel);
    this.router.navigate(['search'], {
      queryParams:
      {
        "userId": this.searchModel.userId,
        "company": this.searchModel.company,
        "skill": this.searchModel.skill,
        "location": this.searchModel.location
      }
    });
  }
  // searchProject() {
  //   this.searchProjectModel.userId = this.userId;
  //   this.router.navigate(['search'], {
  //     queryParams:
  //     {
  //       "PuserId": this.searchProjectModel.userId,
  //       "Pcompany": this.searchProjectModel.company,
  //       "Pskill": this.searchProjectModel.skill,
  //       "Plocation": this.searchProjectModel.location
  //     }
  //   });
  // }

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

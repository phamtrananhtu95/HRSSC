import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../services/project.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Project } from '../../models';
import { ResourceMatchingComponent } from './resource-matching/resource-matching.component';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-project-info',
  templateUrl: './project-info.component.html',
  styleUrls: ['./project-info.component.css']
})
export class ProjectInfoComponent implements OnInit {

  public parentTitle = "Home";
  public title = " - Project";
  public subTitle = " - Information";
  public titleLink = " / Project info";

  // ============================

  public project;
  public skillList = "";
  public isManager: boolean;
  public isChief = false;
  public userId: number;
  public companyID: number;
  public projectId: number;
  public userIdByProjectId: number;
  @ViewChild(ResourceMatchingComponent) resourceMatchingComponent: ResourceMatchingComponent
  public listAvailableResource = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthenticateService,
    private prjService: ProjectService,
    private empService: EmployeeService
  ) { 
    this.route.queryParams.subscribe(param => {
      this.projectId= this.route.snapshot.queryParams['id'];
      this.userId = this.auth.getUserId();
      this.companyID = this.auth.getCompanyId();
      this.getProjectById();
    });
  }

  ngOnInit() {
    // if (this.auth.checkLogin()) {
    //   this.projectId = this.route.snapshot.queryParams['id'];
    //   this.userId = this.auth.getUserId();
    //   this.getProjectById();
    // }
  }

  getProjectById() {
    this.project = new Project();
    this.prjService.getProjectByProjectId(this.userId, this.projectId).subscribe(
      res => {
        this.skillList = "";
        this.project = res;
        this.userIdByProjectId = this.project.userId;
        this.isManager = this.userId === this.userIdByProjectId;

        if(this.isManager == false){
          if(this.companyID === this.project.companyByCompanyId.id){
            this.isChief = true;
          }
        }
        this.project.projectRequirementsById.forEach(el => {
          el.skillRequirementsById.forEach(el2 => {
            this.skillList = this.skillList + el2.skillBySkillId.title + ", ";
          });
        });
        var lastIndex = this.skillList.lastIndexOf(", ");
        this.skillList = this.skillList.substring(0, lastIndex);
      },
      err => {
        this.router.navigate(['home']);
      }
    );

  }
  reloadMatchingResource() {
    this.resourceMatchingComponent.getResourceMatching();
  }
  getAvailableResource() {
    this.empService.getAppliableByManagerId(this.userId, this.projectId).subscribe(
      res => {
        this.listAvailableResource = [];
        res.forEach(el => {
          this.listAvailableResource.push({
            project: this.project,
            resource: el
          })
        });
        console.log(this.listAvailableResource);
      },
      err => {

      }
    );
  }

}

import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee, CompanyEmp, SkillBySkillId, UserByUserId, Project } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectMatchingComponent } from './project-matching/project-matching.component';
import { ProjectService } from '../../services/project.service';
import { ProjectMatch } from '../../models/projectMatched.model';
import { Interaction } from '../../models/interaction.model';

@Component({
  selector: 'app-resource-info',
  templateUrl: './resource-info.component.html',
  styleUrls: ['./resource-info.component.css']
})
export class ResourceInfoComponent implements OnInit {
  public humanResource = new Employee();
  public skillList: string;
  // public avaliableDate: any;
  public userByUserId: number;
  public isOwnManager: boolean;

  public userId: number;
  public resourceId: number;
  @ViewChild(ProjectMatchingComponent) projectMatchingComponent: ProjectMatchingComponent;  //call event child


  // For invite
  public projects: ProjectMatch[];
  public formModel: Interaction;
  public isEmptyProject = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,

    // For invite
    public projectService: ProjectService
  ) { }

  ngOnInit() {
    (<any>window).componentPopup = true;
    this.resourceId = this.route.snapshot.queryParams['id'];
    this.userId = this.authenticateService.getUserId();

    this.getHumanResourceById();

    //For invite
    // this.formModel.humanResourceId;
    // this.formModel.projectId
    
    // this.projectService.getProjectByManagerId(this.userId).subscribe(
    //   res => {
    //     this.projects = res;
    //     console.log(this.projects)
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // )
    //--For invite
  }

  getHumanResourceById() {
    this.skillList = "";
    this.employeeService.getHumanResourceById(this.userId, this.resourceId).subscribe(
      res => {

        this.humanResource = res;
        this.userByUserId = this.humanResource.userByUserId.id;
        this.isOwnManager = this.userId === this.userByUserId;

        console.log(this.isOwnManager);
        // console.log(this.humanResource.availableDate);
        // console.log("----------" + JSON.stringify(this.humanResource));
        let skills = [];
        this.humanResource.resourceSkillsById.forEach(skill => {
          skills.push(skill.skillBySkillId.title);
        })
        this.skillList = skills.join(", ");
      },
      err => {
      }
    )
  }

  reloadMatchingProject() {
    this.projectMatchingComponent.getProjectMatching();
  }

  inviteSuccess(event){
    this.isEmptyProject = (event.length == 0);
  }
}

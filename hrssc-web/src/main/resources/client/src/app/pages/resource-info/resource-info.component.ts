import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee, CompanyEmp, SkillBySkillId, UserByUserId, Project } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectMatchingComponent } from './project-matching/project-matching.component';
import { ProjectService } from '../../services/project.service';
import { ProjectMatch } from '../../models/projectMatched.model';
import { Interaction } from '../../models/interaction.model';
import { Feedback } from '../../models/feedback.model';

@Component({
  selector: 'app-resource-info',
  templateUrl: './resource-info.component.html',
  styleUrls: ['./resource-info.component.css']
})
export class ResourceInfoComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Information";
  public titleLink = " / Resource info";

  // ============================
  public humanResource = new Employee();
  public skillList: string;
  public userByUserId: number;
  public isOwnManager: boolean;
  public userId: number;
  public resourceId: number;

  // Feedback
  public jobKnowledge: number;
  public workQuality: number;
  public cooperation: number;
  public attendance: number;
  public workAttitude: number;

  @ViewChild(ProjectMatchingComponent) projectMatchingComponent: ProjectMatchingComponent;  //call event child


  // For invite
  public projects: ProjectMatch[];
  public formModel: Interaction;
  public isEmptyProject = false;
  public star = 4;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,

    // For invite
    public projectService: ProjectService
  ) {
    this.route.queryParams.subscribe(param => {

      //Reload
      this.resourceId = this.route.snapshot.queryParams['id'];
      this.userId = this.authenticateService.getUserId();
      this.getHumanResourceById();

      // console.log("param------" + param.id)
    });
  }

  ngOnInit() {
    (<any>window).componentPopup = true;

    // this.resourceId = this.route.snapshot.queryParams['id'];
    // this.userId = this.authenticateService.getUserId();
    // this.getHumanResourceById();

    // Feedback
    // this.formFeedback.rating = 4.5;
    // this.formFeedback.comment = "he is very good!!!";
  }

  getHumanResourceById() {
    this.skillList = "";
    this.employeeService.getHumanResourceById(this.userId, this.resourceId).subscribe(
      res => {

        this.humanResource = res;
        this.userByUserId = this.humanResource.userByUserId.id;
        this.isOwnManager = this.userId === this.userByUserId;
        
        this.humanResource.averageRatingsById.forEach(element => {
          this.jobKnowledge = element.jobKnowledge;
          this.workQuality = element.workQuality;
          this.cooperation = element.cooperation;
          this.attendance = element.attendance;
          this.workAttitude = element.workAttitude;

          // let ecec = this.ratingDetailHuman.jobKnowledge;
          // console.log("aaaaaaaa"+JSON.stringify(this.ratingDetailHuman));
        });

        // this.ratingDetailHuman = this.humanResource.averageRatingsById;

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

  inviteSuccess(event) {
    this.isEmptyProject = (event.length == 0);
  }
}

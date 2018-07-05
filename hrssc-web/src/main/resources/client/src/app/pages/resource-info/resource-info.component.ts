import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee, CompanyEmp, SkillBySkillId, UserByUserId } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectMatchingComponent } from './project-matching/project-matching.component';

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

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService
  ) { }

  ngOnInit() {
    (<any>window).componentPopup = true;
    this.resourceId = this.route.snapshot.queryParams['id'];
    this.userId = this.authenticateService.getUserId();
    this.getHumanResourceById();

    // let humanResourceId = this.route.snapshot.queryParams['id'];
    // this.skillList = "";
    // this.employeeService.getHumanResourceById(humanResourceId).subscribe(
    //   res => {
    //     this.avaliableDate = this.ConvertToDatetime(this.humanResource.availableDate);
    //     this.humanResource = res;
    //     this.humanResource.resourceSkillsById.forEach(skill => {
    //       this.skillList = this.skillList + skill.skillBySkillId.title + ", ";
    //     });
    //     console.log("----------" + this.skillList);
    //   },
    //   err => {

    //   }
    // )
  }

  getHumanResourceById() {
    this.skillList = "";
    this.employeeService.getHumanResourceById(this.resourceId).subscribe(
      res => {
        // this.avaliableDate = this.ConvertToDatetime(this.humanResource.availableDate);

        this.humanResource = res;
        this.userByUserId = this.humanResource.userByUserId.id;

        this.isOwnManager = this.userId === this.userByUserId;
        console.log(this.isOwnManager);
        console.log(this.humanResource.availableDate);

        // console.log("----------" + JSON.stringify(this.humanResource));
        let skills = [];
        this.humanResource.resourceSkillsById.forEach(skill => {
          skills.push(skill.skillBySkillId.title);
        })
        this.skillList = skills.join(", ");
        // console.log("iiiiiiiii" + this.userByUserId);
      },
      err => {

      }
    )
  }

  reloadMatchingProject() {
    this.projectMatchingComponent.getProjectMatching();
  }
}

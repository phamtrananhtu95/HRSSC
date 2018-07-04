import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee, CompanyEmp, SkillBySkillId, UserByUserId } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';

@Component({
  selector: 'app-resource-info',
  templateUrl: './resource-info.component.html',
  styleUrls: ['./resource-info.component.css']
})
export class ResourceInfoComponent implements OnInit {
  public humanResource = new Employee();
  public skillList: string;
  // public avaliableDate: any;
  public userId: number;
  public userByUserId: number;
  public isOwnManager: boolean;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService
  ) { }

  ngOnInit() {
    (<any>window).componentPopup = true;
    this.getHumanResourceById();

    this.userId = this.authenticateService.getUserId();

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
    let humanResourceId = this.route.snapshot.queryParams['id'];
    this.skillList = "";
    this.employeeService.getHumanResourceById(humanResourceId).subscribe(
      res => {
        // this.avaliableDate = this.ConvertToDatetime(this.humanResource.availableDate);
        
        this.humanResource = res;
        this.userByUserId = this.humanResource.userByUserId.id;

        this.isOwnManager = this.userId === this.userByUserId;
        // console.log(this.isOwnManager);
        console.log(this.humanResource.availableDate);
        
        // console.log("----------" + JSON.stringify(this.humanResource));
        let skills = this.humanResource.resourceSkillsById;
        if (!skills || skills.length < 1) {
          return;
        }
        for (let i = 0; i < skills.length - 1; i++) {
          this.skillList = this.skillList + skills[i].skillBySkillId.title + ", ";
        }
        this.skillList = this.skillList + skills[skills.length - 1].skillBySkillId.title;
        // console.log("iiiiiiiii" + this.userByUserId);
      },
      err => {

      }
    )
  }

  ConvertToDatetime(dateValue): String {
    if (!dateValue) {
      return null;
    }
    var date = new Date(parseFloat(dateValue));
    var dateParse = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    return dateParse;
  }
}

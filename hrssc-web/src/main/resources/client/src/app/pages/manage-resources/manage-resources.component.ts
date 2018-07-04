import { Component, OnInit } from '@angular/core';
import { User, Employee, SkillBySkillId } from '../../models';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';

@Component({
  selector: 'app-manage-resources',
  templateUrl: './manage-resources.component.html',
  styleUrls: ['./manage-resources.component.css']
})
export class ManageResourcesComponent implements OnInit {
  // public resources: User[]
  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Resource";
  
  public humanResource: Employee[];
  // public humanResource = new Array<Employee>();
  
  public managerId;
  public resourceId;

  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    private router: Router
  ) { }
  
  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;

    this.managerId = this.authenticateService.getUserId();
    this.getHumanResourceByManagerId();

    // (<any>window).formSelect2 = true;
    // (<any>window).interactionsMin = true;
    

  }

  getHumanResourceByManagerId(){
    this.employeeService.getHumanResourceByManagerId(this.managerId).subscribe(
      res => {
        this.humanResource = res;
        console.log(this.humanResource);
        this.bindSkill();
      },
      error => {
        console.log(error);
      }
    )
  }

  bindSkill(){
    this.humanResource.forEach(resource => {
        let skills = resource.resourceSkillsById;
        if (!skills || skills.length < 1) {
          return;
        }
        let skillList = "";
        for (let i = 0; i < skills.length - 1; i++) {
          skillList = skillList + skills[i].skillBySkillId.title + ", ";
        }
        skillList = skillList + skills[skills.length - 1].skillBySkillId.title;
        resource.skill = skillList;
    });
  }
  viewHumanResourceDetail(humanResourceId) {
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": humanResourceId}});
    
  }
}

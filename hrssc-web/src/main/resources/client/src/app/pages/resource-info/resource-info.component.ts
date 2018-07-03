import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee, CompanyEmp, SkillBySkillId } from '../../models';

@Component({
  selector: 'app-resource-info',
  templateUrl: './resource-info.component.html',
  styleUrls: ['./resource-info.component.css']
})
export class ResourceInfoComponent implements OnInit {
  public humanResource = new Employee();
  public skillList: string;
  public avaliableDate: any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private employeeService: EmployeeService
  ) { }

  ngOnInit() {
    (<any>window).componentPopup = true;
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
    let humanResourceId = this.route.snapshot.queryParams['id'];
    this.skillList = "";
    this.employeeService.getHumanResourceById(humanResourceId).subscribe(
      res => {
        this.avaliableDate = this.ConvertToDatetime(this.humanResource.availableDate);
        this.humanResource = res;
        this.humanResource.resourceSkillsById.forEach(skill => {
          this.skillList = this.skillList + skill.skillBySkillId.title + ", ";
        });
        console.log("----------" + this.skillList);
      },
      err => {

      }
    )
  }

  ConvertToDatetime(dateValue) {
    if (!dateValue) {
      return null;
    }
    var date = new Date(parseFloat(dateValue));
    var dateParse = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    return dateParse;
  }
}

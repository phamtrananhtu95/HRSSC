import { Component, OnInit } from '@angular/core';
import { Employee, Project } from '../../models';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IMyDpOptions, IMyDateModel } from 'angular4-datepicker/src/my-date-picker';

@Component({
  selector: 'app-job-contract',
  templateUrl: './job-contract.component.html',
  styleUrls: ['./job-contract.component.css']
})
export class JobContractComponent implements OnInit {

  public humanResource = new Employee();
  public project: any;
  // public skillList: string;
  // public avaliableDate: any;
  public userByUserId: number;
  public isCheckTerm = false;

  public userId: number;
  public resourceId: number;

  public myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'dd/mm/yyyy',
  };

  public isEditMode: boolean;
  startDate: any = null;
  endDate: any = null;

  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    public projectService: ProjectService
  ) {
    this.userId = this.authenticateService.getUserId();
    this.getHumanResourceById();
    this.getProject();
  }

  ngOnInit() {
    if (this.humanResource) {
      this.startDate = this.ConvertToDatetime(this.humanResource.availableDate);
      this.endDate = this.ConvertToDatetime(this.humanResource.availableDuration);
    }
  }

  ngOnChanges() {
  }

  getHumanResourceById() {
    this.employeeService.getHumanResourceById(this.userId, 5).subscribe(
      res => {
        this.humanResource = res;
        this.startDate = this.ConvertToDatetime(this.humanResource.availableDate);
        this.endDate = this.ConvertToDatetime(this.humanResource.availableDuration);
        this.userByUserId = this.humanResource.userByUserId.id;

        // console.log("----------" + JSON.stringify(this.humanResource));
      },
      err => {
      }
    )
  }

  getProject() {
    this.projectService.getProjectByProjectId(this.userId, 13).subscribe(
      res => {
        this.project = res;
        console.log("----------" + JSON.stringify(this.project));
      },
      err => {
      }
    )
  }

  ConvertToDatetime(dateValue) {
    if (!dateValue) {
      return null;
    }
    var date = new Date(dateValue);
    var dateParse = {
      date: {
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        day: date.getDate()
      }
    };
    return dateParse;
  }

  onDateChangedCreate(event: IMyDateModel) {
    this.humanResource.availableDate = event && event.jsdate ? event.jsdate.getTime() : null;
  }

  onDateChangedEnd(event: IMyDateModel) {
    this.humanResource.availableDuration = event && event.jsdate ? event.jsdate.getTime() : null;
  }

  onValueChangeCheckTerm($event) {
    this.isCheckTerm = !this.isCheckTerm;
  }

  onChangeEditMode(){
    this.isEditMode= !this.isEditMode;
    this.isCheckTerm = false;
  }
}

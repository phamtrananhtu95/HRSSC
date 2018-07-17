import { Component, OnInit } from '@angular/core';
import { Employee, Project } from '../../models';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IMyDpOptions, IMyDateModel } from 'angular4-datepicker/src/my-date-picker';
import { ActivatedRoute, Router } from '@angular/router';
import { Interaction } from '../../models/interaction.model';
import { ContractService } from '../../services/contract.service';

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
  public isCheckTerm = false;

  public userId: number;

  public myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'dd/mm/yyyy',
  };

  public isEditMode: boolean;
  public formContract = new Interaction();
  startDate: any = null;
  endDate: any = null;
  public composeContract = false;
  public isEditable: boolean;
  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    public projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router,
    private contractService: ContractService
  ) {
    this.userId = this.authenticateService.getUserId();
    this.route.queryParams.subscribe(params => {
      if (!params) {
        return;
      }

      this.composeContract = params.composeContract === 'true';
      if (this.composeContract) {
        this.isEditMode = true;
        this.isEditable = true;
        let resourceId = params.humanResourceId;
        let projectId = params.projectId;
        this.formContract.humanResourceId = resourceId;
        this.formContract.projectId = projectId;
        this.getHumanResourceById(resourceId, true);
        this.getProject(projectId);
      } else {
        this.getContractInfo(params.interactionsId);
      }

    });
  }

  ngOnInit() {
  }

  getContractInfo(invitationId) {
    this.contractService.getContractInfo(invitationId).subscribe(
      res => {
        this.formContract = res;
        this.isEditable = this.formContract.contractByContractId.latestEditorId !== this.userId;
        this.startDate = this.ConvertToDatetime(this.formContract.contractByContractId.startDate);
        this.endDate = this.ConvertToDatetime(this.formContract.contractByContractId.endDate);
        this.getHumanResourceById(this.formContract.humanResourceId);
        this.getProject(this.formContract.projectId);
      }
    )
  }

  getHumanResourceById(resourceId, initFormContract?: boolean) {
    this.employeeService.getHumanResourceById(this.userId, resourceId).subscribe(
      res => {
        this.humanResource = res;
        if (!initFormContract) {
          return;
        }
        this.startDate = this.ConvertToDatetime(this.humanResource.availableDate);
        this.endDate = this.ConvertToDatetime(this.humanResource.availableDuration);
        this.formContract.contractByContractId.salary = this.humanResource.salary;
      },
      err => {
      }
    )
  }

  getProject(projectId) {
    this.projectService.getProjectByProjectId(this.userId, projectId).subscribe(
      res => {
        this.project = res;
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
    this.formContract.contractByContractId.startDate = event && event.jsdate ? event.jsdate.getTime() : null;
  }

  onDateChangedEnd(event: IMyDateModel) {
    this.humanResource.availableDuration = event && event.jsdate ? event.jsdate.getTime() : null;
    this.formContract.contractByContractId.endDate = event && event.jsdate ? event.jsdate.getTime() : null;
  }

  onValueChangeCheckTerm($event) {
    this.isCheckTerm = !this.isCheckTerm;
  }

  onChangeEditMode() {
    this.isEditMode = !this.isEditMode;
    this.isCheckTerm = false;
  }

  sendOffer() {
    if (this.composeContract) {
      this.employeeService.inviteHumanResource(this.formContract).subscribe(
        res => {
          this.router.navigate(['home']);
        },
        err => {
          console.log(err);
        }
      );
    } else {
      this.formContract.contractByContractId.latestEditorId = this.userId;
      this.contractService.changeOffer(this.formContract.contractByContractId).subscribe(
        res => {
          this.router.navigate(['manager/invitation']);
        },
        err => {
          console.log(err);
        }
      );
    }
  }

  showChatPopup() {
    $('#qnimate').addClass('popup-box-on');
  }

  removeChatBox() {
    $('#qnimate').removeClass('popup-box-on');
  }

  // inviteHumanResource(projectId) {
  //   this.formContract.projectId = this.projectId;
  //   this.formContract.humanResourceId = this.resourceId;
  //   this.formContract.contractByContractId.salary=this.humanResource.salary;
  //   this.formContract.contractByContractId.startDate=this.humanResource.availableDate;
  //   this.formContract.contractByContractId.endDate=this.humanResource.availableDuration;

  //   console.log("----------" + JSON.stringify(this.formContract));

  // this.employeeService.inviteHumanResource(this.formContract).subscribe(
  //   res=>{

  //   },
  //   err => {
  //     console.log(err);
  //   }
  // )
  // }
}

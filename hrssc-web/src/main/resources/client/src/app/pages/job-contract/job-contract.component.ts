import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Employee, Project } from '../../models';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IMyDpOptions, IMyDateModel } from 'angular4-datepicker/src/my-date-picker';
import { ActivatedRoute, Router } from '@angular/router';
import { Interaction, ContractByContractId } from '../../models/interaction.model';
import { ContractService } from '../../services/contract.service';
declare var $: any;
import { InvitationService } from '../../services/invitation.service';
import { ChatService } from '../../services/chat.service';
import { ToastsManager } from 'ng2-toastr';

@Component({
  selector: 'app-job-contract',
  templateUrl: './job-contract.component.html',
  styleUrls: ['./job-contract.component.css']
})
export class JobContractComponent implements OnInit {

  public humanResource = new Employee();
  public project: any;
  public contractVersion: any;
  public contractOpts;

  public isCheckTerm = false;

  public userId: number;
  public contractid: number;

  // date
  public today: FormGroup;
  startDate: any = null;
  endDate: any = null;

  public myDatePickerOptionsStart: IMyDpOptions = {
    dateFormat: 'dd/mm/yyyy',
  };

  public myDatePickerOptionsEnd: IMyDpOptions = {
    dateFormat: 'dd/mm/yyyy',
  };

  public isEditMode: boolean;
  public formContract = new Interaction();
  public composeContract = false;
  public isEditable: boolean;
  public formOffer = new ContractByContractId();
  public show: boolean = false;


  // Call api invite or apply
  public isInvite = false;
  public currentVersionInfo: ContractByContractId;
  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    public projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router,
    private contractService: ContractService,
    private invitationService: InvitationService,
    private chatService: ChatService,

    private formBuilder: FormBuilder,

    private toastr: ToastsManager,
    public vcr: ViewContainerRef,
  ) {
    this.toastr.setRootViewContainerRef(vcr);

    this.userId = this.authenticateService.getUserId();
    this.route.queryParams.subscribe(params => {
      if (!params) {
        return;
      }

      this.composeContract = params.composeContract === 'true';

      // 
      this.isInvite = params.isInvite === 'true';
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
    (<any>window).sweetAlertMin = true;
    (<any>window).componentModalsJs = true;

    this.today = this.formBuilder.group({
      // Empty string or null means no initial value. Can be also specific date for
      // example: {date: {year: 2018, month: 10, day: 9}} which sets this date to initial
      // value.

      myDate: [null, Validators.required]
      // other controls are here...
    });
    this.setDisableUntilForStartDate();
  }

  setDisableUntilForStartDate() {
    let now = new Date();
    let optionsStart = JSON.parse(JSON.stringify(this.myDatePickerOptionsStart));
    optionsStart.disableUntil = { year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate() };
    this.myDatePickerOptionsStart = optionsStart;
  }

  getContractInfo(invitationId) {
    this.contractService.getContractInfo(invitationId).subscribe(
      res => {
        console.log(res);

        this.formContract = res;
        this.contractid = this.formContract.contractByContractId.id;
        this.isEditable = this.formContract.contractByContractId.latestEditorId !== this.userId;
        this.convertDateForPicker();
        this.getHumanResourceById(this.formContract.humanResourceId);
        this.getProject(this.formContract.projectId);
        this.currentVersionInfo = JSON.parse(JSON.stringify(this.formContract.contractByContractId));
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
    let startDate = event.date;
    let optionsEnd = JSON.parse(JSON.stringify(this.myDatePickerOptionsEnd));
    optionsEnd.disableUntil = startDate;
    this.myDatePickerOptionsEnd = optionsEnd;
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
    this.getcontractOpts();
  }

  sendOffer() {
    debugger;
    // let msg = "has been Invite";
    // let notiType = "Invite";
    // let userId = this.humanResource.userByUserId.id;

    // demo send notify
    // let msg = "aa";
    // let sendTo = "abc@abc.com"
    // this.chatService.sendNotify(msg, sendTo);

    // this.chatService.sendNotify(msg, notiType, this.formContract.projectId, this.formContract.humanResourceId, userId);

    if (this.composeContract) {

      if (this.isInvite) {
        // console.log("invite á em");
        this.showSendInviteSuccess();
        this.formContract.contractByContractId.latestEditorId = this.userId;
        this.employeeService.inviteHumanResource(this.formContract).subscribe(
          res => {
            // noti for invitation
            let companyName = this.project.companyByCompanyId.name
            let notiType = "Invite";
            let msg = companyName + " " + notiType + " " + this.humanResource.fullname + " to " + this.project.title + " project";

            let userId = this.humanResource.userByUserId.id;
            this.chatService.sendNotify(msg, notiType, this.formContract.projectId, this.formContract.humanResourceId, userId);

            this.router.navigate(['home']);
            // send notify

          },
          err => {
            console.log(err);
          }
        );
      }
      else {
        // console.log("aply á em");
        this.showSendApplySuccess();
        this.projectService.applyResource(this.formContract).subscribe(
          res => {
            // noti for appliances
            let companyName = this.humanResource.companyByCompanyId.name;
            let notiType = "Apply";
            let msg = this.humanResource.fullname + " of " + companyName + "want to " + notiType + " " + " to " + this.project.title + " project";
            let userId = this.project.userId;
            this.chatService.sendNotify(msg, notiType, this.formContract.projectId, this.formContract.humanResourceId, userId);
            this.router.navigate(['home']);
          },
          err => {
            console.log(err);
          }
        );
      }
    } else {
      this.showSendOfferSuccess();
      
      let notiUserid = this.formContract.contractByContractId.latestEditorId;
      this.formContract.contractByContractId.latestEditorId = this.userId;
      
      this.contractService.changeOffer(this.formContract.contractByContractId).subscribe(
        res => {
          // OFFER_DEALING
          let companyName = this.project.companyByCompanyId.name;
          let notiType = "Offer Dealing";
          let msg = "New " + notiType + " of " + companyName + " company at " + this.project.title + " project";
          this.chatService.sendNotify(msg, notiType, this.formContract.projectId, this.formContract.humanResourceId, notiUserid);

          this.router.navigate(['home']);
        },
        err => {
          console.log(err);
        }
      );
    }
  }

  acceptOffer() {
    this.formOffer.id = this.formContract.contractId;
    this.formOffer.latestEditorId = this.userId;
    // console.log("----------" + JSON.stringify(this.formOffer));));
    let notiUserid = this.formContract.contractByContractId.latestEditorId;



    this.contractService.acceptOffer(this.formOffer).subscribe(
      res => {
        let companyName = this.project.companyByCompanyId.name;
        let notiType = "Accept offer";
        let msg = "Accept offer";
        this.chatService.sendNotify(msg, notiType, this.formContract.projectId, this.formContract.humanResourceId, notiUserid);
        this.router.navigate(['home']);
      }
    );
  }

  getcontractOpts() {
    // contractid
    this.contractService.loadContractVersions(this.contractid).subscribe(
      res => {
        this.contractOpts = [];
        for (let i = res.length - 1; i >= 0; i--) {
          let contractVer = res[i];
          // var dealDateParse = parseInt(contractVer.dealDate);
          // var date = new Date(dealDateParse);
          var dateConvert = this.ConvertToDatetime(contractVer.dealDate * 1000);
          var dd = dateConvert.date.day;
          var mm = dateConvert.date.month;
          var yyyy = dateConvert.date.year;
          var today = 'Version ' + (res.length - i) + ': ' + dd + '/' + mm + '/' + yyyy;

          // console.log("deal:-----------" + version + " " + contractVer.dealDate);
          // console.log("Deal date-----------" + JSON.stringify(dateConvert));

          this.contractOpts.push({ value: contractVer, label: today });
        }
        // res.forEach(contractVer => {
        //   // var dealDateParse = parseInt(contractVer.dealDate);
        //   // var date = new Date(dealDateParse);
        //   var dateConvert = this.ConvertToDatetime(contractVer.dealDate * 1000);
        //   var dd = dateConvert.date.day;
        //   var mm = dateConvert.date.month;
        //   var yyyy = dateConvert.date.year;
        //   version = version + 1;
        //   var today = 'Version ' + version + ': ' + dd + '/' + mm + '/' + yyyy;

        //   // console.log("deal:-----------" + version + " " + contractVer.dealDate);
        //   // console.log("Deal date-----------" + JSON.stringify(dateConvert));

        //   this.contractOpts.push({ value: contractVer, label: today });
        // });
        // console.log("contract-----------" + JSON.stringify(this.contractOpts));
      },
      err => {
        console.log(err);
      }
    )

  }

  changeContractVersion() {
    window.alert("hihi");
  }

  showChatPopup() {
    $('#qnimate').addClass('popup-box-on');
  }
  showChat() {
    this.show = !this.show;
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

  loadContractByVersion() {
    this.formContract.contractByContractId.additionalTerms = this.contractVersion.additionalTerms;
    this.formContract.contractByContractId.salary = this.contractVersion.salary;
    this.formContract.contractByContractId.startDate = this.contractVersion.startDate;
    this.formContract.contractByContractId.endDate = this.contractVersion.endDate;
    this.convertDateForPicker();
  }

  revertVersionInfo() {
    this.formContract.contractByContractId = JSON.parse(JSON.stringify(this.currentVersionInfo));
    this.convertDateForPicker();
  }

  private convertDateForPicker() {
    this.startDate = this.ConvertToDatetime(this.formContract.contractByContractId.startDate);
    this.endDate = this.ConvertToDatetime(this.formContract.contractByContractId.endDate);
  }

  cancelOffer() {
    this.contractService.cancelOffer(this.contractid).subscribe(
      res => {
        this.router.navigate(['home']);
      },
      err => {
        console.log(err);
      })
  }

  showSendOfferSuccess() {
    this.toastr.success('Send offer success!', 'Success!', {toastLife: 2000});
  }
  showSendInviteSuccess() {
    this.toastr.success('Send offer success!', 'Success!', {toastLife: 2000});
  }
  showSendApplySuccess() {
    this.toastr.success('Send offer success!', 'Success!', {toastLife: 2000});
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContractService } from '../../services/contract.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Feedback } from '../../models/feedback.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-view-contract',
  templateUrl: './view-contract.component.html',
  styleUrls: ['./view-contract.component.css']
})
export class ViewContractComponent implements OnInit {

  public jobId: number;
  public contractDetail: any;
  public userId: number;
  public feedbackForm = new Feedback();
  public isShowButton = true;

  constructor(
    private route: ActivatedRoute,
    private contractService: ContractService,
    private authen: AuthenticateService,
    private employeeService: EmployeeService,
    private router: Router
  ) {
    this.route.queryParams.subscribe(param => {
      this.jobId = this.route.snapshot.queryParams['id'];
    });
    this.userId = this.authen.getUserId();
  }

  ngOnInit() {
    this.loadContractInfo();
  }

  loadContractInfo() {
    this.contractService.loadContractInfo(this.jobId).subscribe(
      res => {
        this.contractDetail = res;
      }
    );
  }

  cancelHumanResource(jobId) {
    this.contractService.cancelHumanResource(jobId, this.userId).subscribe(
      res => {
        if (res == true) {
          (<any>$("#ratingHumanResource")).modal("show");
          // this.isShowButton = false;
        } else {
          this.loadContractInfo();
          // this.isShowButton = false;
        }
      },
      err => {
        console.log(err);
      }
    )
  }

  feedbackResource(jobId) {

    this.feedbackForm.userId = this.userId;
    this.feedbackForm.jobId = jobId;

    this.employeeService.feedbackResource(this.feedbackForm).subscribe(
      res => {
        // this.getJoinedResource();
        (<any>$("#ratingHumanResource")).modal("hide");
        this.feedbackForm = new Feedback();
      },
      err => {
        console.log(err);
      }
    );
  }
  viewLogChatContract(id) {
    console.log("");
    
  }
}

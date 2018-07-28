import { Component, OnInit } from '@angular/core';
import { Feedback } from '../../models/feedback.model';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Feedback";
  // public subTitle = " - Resource";
  public titleLink = " / Feedback";

  // =============================
  public feedbackForm = new Feedback();
  public userId: number;
  public projectId: number;
  public listHumanOfProject: any;

  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    private route: ActivatedRoute,
  ) {
    this.userId = this.authenticateService.getUserId();
  }

  // public star = 4;
  ngOnInit() {
    this.reloadLibrary();

    this.projectId = this.route.snapshot.queryParams['projectId'];
    this.loadHumanOfProject();
  }

  reloadLibrary(){
    (<any>window).sweetAlertMin = true;
    (<any>window).componentModalsJs = true;
  }

  feedbackResource(jobId) {
    this.feedbackForm.userId = this.userId;
    this.feedbackForm.jobId = jobId;

    console.log(this.feedbackForm);
    
    // this.employeeService.feedbackResource(this.feedbackForm).subscribe(
    //   res => {
    //     // this.reloadHumanList.emit();

    //     this.loadHumanOfProject();
    //     (<any>$("#humanResource" + jobId)).modal("hide");

    //     this.feedbackForm = new Feedback();
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
    // console.log("ec ec: " + JSON.stringify(this.feedbackForm));
  }

  loadHumanOfProject() {
    this.employeeService.loadHumanOfProject(this.projectId).subscribe(
      res => {
        this.listHumanOfProject = res;
        console.log("ec ec: " + JSON.stringify(this.listHumanOfProject));

      },
      err => {
        console.log(err);
      }
    )
  }

}

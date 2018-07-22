import { Component, OnInit } from '@angular/core';
import { Feedback } from '../../models/feedback.model';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  public feedbackForm = new Feedback();
  public userId: number;

  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService
  ) {
    this.userId = this.authenticateService.getUserId();
  }

  // public star = 4;
  ngOnInit() {
  }

  feedbackResource() {
    this.feedbackForm.userId = this.userId;
    this.feedbackForm.jobId = 412;
    this.employeeService.feedbackResource(this.feedbackForm).subscribe(
      res => {
        // this.reloadHumanList.emit();
        (<any>$("#modal_default")).modal("hide");
      },
      err => {
        console.log(err);
      }
    );
    // console.log("ec ec: " + JSON.stringify(this.feedbackForm));
  }

}

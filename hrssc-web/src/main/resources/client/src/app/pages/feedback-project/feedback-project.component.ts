import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feedback-project',
  templateUrl: './feedback-project.component.html',
  styleUrls: ['./feedback-project.component.css']
})
export class FeedbackProjectComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Feedback";
  public titleLink = " / Feedback";

  // =============================
  public userId: number;
  public projectNotFeedback: any;
  public jobCount;

  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    private router: Router,

  ) {
    this.userId = this.authenticateService.getUserId();
  }

  ngOnInit() {
    this.loadProjectNotFeedback();
  }

  loadProjectNotFeedback() {
    this.employeeService.loadProjectNotFeedback(this.userId).subscribe(
      res => {
        this.projectNotFeedback = res;

        this.projectNotFeedback.forEach(prj => {
          this.jobCount = prj.jobsById.length;
          console.log("ec ec: " + this.jobCount);
        });

        // this.jobCount = this.projectNotFeedback.
      },
      err => {
        console.log(err);
      }
    )
  }

  viewListHumanOnProject(projectId) {
    this.router.navigate(['rating'], { queryParams: { "projectId": projectId } });
  }

}

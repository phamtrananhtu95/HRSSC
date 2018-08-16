import { Component, OnInit } from '@angular/core';
import { Feedback } from '../../models/feedback.model';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ActivatedRoute, Router } from '@angular/router';
import { humanResourceById } from '../../models/resourceMatched.model';
import { ChatService } from '../../services/chat.service';

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
    private chatService: ChatService,
    private router: Router,
    
  ) {
    this.userId = this.authenticateService.getUserId();
  }

  // public star = 4;
  ngOnInit() {
    this.reloadLibrary();

    this.projectId = this.route.snapshot.queryParams['projectId'];
    this.loadHumanOfProject();
  }

  reloadLibrary() {
    (<any>window).sweetAlertMin = true;
    (<any>window).componentModalsJs = true;
  }

  feedbackResource(humanResource) {

    this.feedbackForm.userId = this.userId;
    this.feedbackForm.jobId = humanResource.id;

    let companyName = humanResource.humanResourceByHumanResourceId.companyByCompanyId.name;

    this.employeeService.feedbackResource(this.feedbackForm).subscribe(
      res => {
        // this.reloadHumanList.emit();

        // Noti for feedback
        let notiType = "Feedback Committed";
        let msg = "Feedback ";
        let userId = humanResource.humanResourceByHumanResourceId.userByUserId.id;
        this.chatService.sendNotify(msg, notiType, this.projectId, humanResource.humanResourceId, userId);

        this.loadHumanOfProject();
        (<any>$("#humanResource" + humanResource.id)).modal("hide");

        this.feedbackForm = new Feedback();
      },
      err => {
        console.log(err);
      }
    );
    console.log("ec ec: " + JSON.stringify(this.feedbackForm));
  }

  loadHumanOfProject() {
    this.employeeService.loadHumanOfProject(this.projectId).subscribe(
      res => {
        this.listHumanOfProject = res;
        console.log(this.listHumanOfProject);

      },
      err => {
        console.log(err);
      }
    )
  }

  skipFeedback() {
    this.router.navigate(['home']);
  }

}

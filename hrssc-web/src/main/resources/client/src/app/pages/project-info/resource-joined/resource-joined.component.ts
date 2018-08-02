import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../../services/project.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { Feedback } from '../../../models/feedback.model';
import { EmployeeService } from '../../../services/employee.service';
import { ChatService } from '../../../services/chat.service';

@Component({
  selector: 'app-resource-joined',
  templateUrl: './resource-joined.component.html',
  styleUrls: ['./resource-joined.component.css']
})
export class ResourceJoinedComponent implements OnInit {

  public projectId: number;
  public listResourceJoined = [];
  public userId: number;
  public feedbackForm = new Feedback();

  public isKick = false;

  constructor(
    private route: ActivatedRoute,
    private prjService: ProjectService,
    private authen: AuthenticateService,
    private router: Router,
    private employeeService: EmployeeService,
    private chatService: ChatService
  ) {
    this.userId = this.authen.getUserId();
  }

  ngOnInit() {
    this.projectId = this.route.snapshot.queryParams['id'];
    this.getJoinedResource();
  }

  getJoinedResource() {
    this.prjService.getJoinedResourceList(this.projectId).subscribe(
      res => {
        this.listResourceJoined = [];
        this.listResourceJoined = res;
      },
      err => {

      }
    );
  }

  cancelHumanResource(jobId) {
    this.prjService.cancelHumanResource(jobId, this.userId).subscribe(
      res => {
        if (res == true) {

          (<any>$("#ratingHumanResource")).modal("show");
        } else {
          this.getJoinedResource();
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

    // let companyName = humanResource.humanResourceByHumanResourceId.companyByCompanyId.name;

    this.employeeService.feedbackResource(this.feedbackForm).subscribe(
      res => {

        // Noti for feedback
        // let notiType = "Feedback Committed";
        // let msg = "Feedback";
        // let userId = humanResource.humanResourceByHumanResourceId.userByUserId.id;
        // this.chatService.sendNotify(msg, notiType, this.projectId, humanResource.humanResourceId, userId);

        this.getJoinedResource();
        (<any>$("#ratingHumanResource")).modal("hide");
        this.feedbackForm = new Feedback();
      },
      err => {
        console.log(err);
      }
    );
  }

  // backup

  releaseOrKick() {
    this.isKick = true;
    // console.log("aaaaaaaaaaa"+this.isRelease);
  }

  releaseResource(jobId) {
    if (this.isKick == true) {
      // console.log("is kick");
      this.prjService.kickresource(jobId, this.userId).subscribe(
        res => {
          this.getJoinedResource();
          (<any>$("#modal_small")).modal("hide");
        }
      )
    }
    else {
      // console.log("not kick");
      this.prjService.releaseResource(jobId, this.userId).subscribe(
        res => {
          this.getJoinedResource();
          (<any>$("#modal_small")).modal("hide");
          // this.router.navigate(['rating'], { queryParams: { "projectId": projectId } });
        }
      )
      console.log(this.userId + "jobId: " + jobId);
    }

  }
}

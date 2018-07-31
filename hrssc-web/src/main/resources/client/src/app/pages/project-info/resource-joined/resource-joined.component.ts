import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../../services/project.service';
import { AuthenticateService } from '../../../services/authenticate.service';

@Component({
  selector: 'app-resource-joined',
  templateUrl: './resource-joined.component.html',
  styleUrls: ['./resource-joined.component.css']
})
export class ResourceJoinedComponent implements OnInit {

  public projectId: number;
  public listResourceJoined = [];
  public userId: number;

  public isKick = false;

  constructor(
    private route: ActivatedRoute,
    private prjService: ProjectService,
    private authen: AuthenticateService,
    private router: Router,
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

  releaseOrKick() {
    this.isKick = true;
    // console.log("aaaaaaaaaaa"+this.isRelease);
  }

  releaseResource(jobId, projectId) {
    if (this.isKick == true) {
      // console.log("is kick");
      this.prjService.kickresource(jobId, this.userId).subscribe(
        res => {
          this.getJoinedResource();
          // this.router.navigate(['rating'], { queryParams: { "projectId": projectId } });
        }
      )
    }
    else {
      // console.log("not kick");
      this.prjService.releaseResource(jobId, this.userId).subscribe(
        res => {
          this.getJoinedResource();
          // this.router.navigate(['rating'], { queryParams: { "projectId": projectId } });
        }
      )
      console.log(this.userId + "jobId: " + jobId);
    }

  }
}

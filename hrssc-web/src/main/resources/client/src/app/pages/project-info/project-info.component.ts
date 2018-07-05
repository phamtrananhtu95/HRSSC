import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../services/project.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Project } from '../../models';
import { ResourceMatchingComponent } from './resource-matching/resource-matching.component';

@Component({
  selector: 'app-project-info',
  templateUrl: './project-info.component.html',
  styleUrls: ['./project-info.component.css']
})
export class ProjectInfoComponent implements OnInit {
  public project = new Project();
  public skillList = "";
  public isManager: boolean;
  public userId: number;
  public projectId: number;
  public userIdByProjectId: number;
  @ViewChild(ResourceMatchingComponent) resourceMatchingComponent: ResourceMatchingComponent

  constructor(
    private route: ActivatedRoute,
    private auth: AuthenticateService,
    private prjService: ProjectService
  ) { }

  ngOnInit() {
    if (this.auth.checkLogin()) {
      this.projectId = this.route.snapshot.queryParams['id'];
      this.userId = this.auth.getUserId();
      this.getProjectById();
    }
  }

  getProjectById() {


    this.prjService.getProjectByProjectId(this.userId, this.projectId).subscribe(
      res => {
        this.project = res;
        this.userIdByProjectId = this.project.userId;
        this.isManager = this.userId === this.userId;
        console.log(this.isManager);
          this.project.projectRequirementsById.forEach(el => {
            el.skillRequirementsById.forEach(el2 => {
              this.skillList = this.skillList + el2.skillBySkillId.title + ", ";
            });
          });
        var lastIndex = this.skillList.lastIndexOf(", ");
        this.skillList = this.skillList.substring(0, lastIndex);
      },
      err => {

      }
    );

  }
  reloadMatchingResource() {
    this.resourceMatchingComponent.getResourceMatching();
  }

}

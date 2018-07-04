import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../services/project.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Project } from '../../models';

@Component({
  selector: 'app-project-info',
  templateUrl: './project-info.component.html',
  styleUrls: ['./project-info.component.css']
})
export class ProjectInfoComponent implements OnInit {
  public project = new Project();
  public skillList= "";

  constructor(
    private route: ActivatedRoute,
    private auth: AuthenticateService,
    private prjService: ProjectService
  ) { }

  ngOnInit() {
    if(this.auth.checkLogin()){
      this.getProjectById();
    }
  }

  getProjectById(){
    let projectId = this.route.snapshot.queryParams['id'];
    let userId = this.auth.getUserId();
    this.prjService.getProjectByProjectId(userId, projectId).subscribe(
      res => {
        this.project = res;
        console.log(this.project);
        this.project.projectRequirementsById.forEach(el => {
          el.skillRequirementsById.forEach(el2 => {
            this.skillList  = this.skillList + el2.skillBySkillId.title+ ", ";
          });
        });
        var lastIndex = this.skillList.lastIndexOf(", ");
        this.skillList =this.skillList.substring(0, lastIndex);
      },
      err => {

      }
    );

  }

}

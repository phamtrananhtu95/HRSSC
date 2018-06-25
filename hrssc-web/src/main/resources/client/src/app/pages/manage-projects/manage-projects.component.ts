import { Component, OnInit } from '@angular/core';
import { Project, projectList } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';

@Component({
  selector: 'app-manage-projects',
  templateUrl: './manage-projects.component.html',
  styleUrls: ['./manage-projects.component.css']
})
export class ManageProjectsComponent implements OnInit {
  
  public parentTitle = "Home";
  public title = " - Manage projects";
  public subTitle = " - Project";
  public projects: Project[];

  constructor(
    private auth : AuthenticateService,
    private prjService: ProjectService
  ) { }

  ngOnInit() {
    // this.projects = new projectList().projects;
    if(this.auth.checkLogin()){
      let userInfo = this.auth.getUserInfo();
      this.prjService.getProjectByManagerId(userInfo.id).subscribe(
        res => {
          this.projects = res;
        },
        err=>{
          console.log(err);
        }
      );
    }
    
  }

}

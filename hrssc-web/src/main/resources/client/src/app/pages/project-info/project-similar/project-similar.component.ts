import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../../../services/project.service';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { ProjectSimilar } from '../../../models';
declare var $: any;

@Component({
  selector: 'app-project-similar',
  templateUrl: './project-similar.component.html',
  styleUrls: ['./project-similar.component.css']
})
export class ProjectSimilarComponent implements OnInit {
  public projectId: number;
  public listSimilarProject: Array<ProjectSimilar>;
  public skillListtmp;
  constructor(
    private route: ActivatedRoute,
    private prjService: ProjectService,
    private router: Router
  ) { 
    this.route.queryParams.subscribe(param => {
      this.projectId= this.route.snapshot.queryParams['id'];
      this.getSimilarProject();
    });
  }

  ngOnInit() {
    this.projectId = this.route.snapshot.queryParams['id'];
    this.getSimilarProject();

    // this.router.routeReuseStrategy.shouldReuseRoute = function () {
    //   return false;
    // };

    // this.router.events.subscribe((evt) => {
    //   if (evt instanceof NavigationEnd) {
    //     this.router.navigated = false;
    //     window.scrollTo(0, 0);
    //   }
    // });
  }
  // ngOnChanges(){
  //   this.projectId = this.route.snapshot.queryParams['id'];
  //   this.getSimilarProject();
  // }

  getSimilarProject() {
    this.prjService.getSimilarProject(this.projectId).subscribe(
      res => {
        this.listSimilarProject = new Array<ProjectSimilar>();

        this.listSimilarProject = res;
        console.log(this.listSimilarProject);
        

        this.listSimilarProject.forEach(el => {
          el.projectBySimilarProjectId.duration = this.ConvertToDatetime(el.projectBySimilarProjectId.duration);
          el.projectBySimilarProjectId.endDate = this.ConvertToDatetime(el.projectBySimilarProjectId.endDate);

          el.listSkillTitle = [];
          this.skillListtmp = [];
          let projectByProjectId = el.projectBySimilarProjectId;
          projectByProjectId.projectRequirementsById.forEach(projectReq => {
            projectReq.skillRequirementsById.forEach(skillReq => {
              this.skillListtmp.push(skillReq.skillBySkillId.title);
            });
          });
          el.listSkillTitle.push(this.skillListtmp);
        });
        this.listSimilarProject.forEach(el => {
          el.listSkillTitle = this.removeDuplicateUsingSet(el.listSkillTitle);
        });
      },
      err => {

      }
    );
  }

  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    return dateParse;
  }

  navigateProject(id) {
    this.router.navigate(['manager/project/info'], { queryParams: { "id": id } });
    // window.location.reload();
  }

  removeDuplicateUsingSet(arr) {
    let unique_array = Array.from(new Set(arr))
    return unique_array
  }

  viewCompanyDetail(companyId) {
    this.router.navigate(['company/info'], {queryParams:{"id": companyId}});
  }
}

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
  ) { }

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

  getSimilarProject() {
    this.prjService.getSimilarProject(this.projectId).subscribe(
      res => {
        this.listSimilarProject = new Array<ProjectSimilar>();

        this.listSimilarProject = res;
        this.listSimilarProject.forEach(el => {
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

  navigateProject(id) {
    this.router.navigate(['manager/project/info'], { queryParams: { "id": id } });
    // window.location.reload();
  }

  removeDuplicateUsingSet(arr) {
    let unique_array = Array.from(new Set(arr))
    return unique_array
  }
}

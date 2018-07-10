import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../../../services/project.service';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-project-similar',
  templateUrl: './project-similar.component.html',
  styleUrls: ['./project-similar.component.css']
})
export class ProjectSimilarComponent implements OnInit {
  public projectId: number;
  public listSimilarProject;
  public skillList;
  public test = [];
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
        this.listSimilarProject = [];
        this.skillList = [];
        this.listSimilarProject = res;
        res.forEach(el => {
          this.skillList.push(el.projectBySimilarProjectId.projectRequirementsById);
        });
        // this.skillList.forEach(el => {
        //   el.forEach(el2 => {
        //     this.test.push({
        //       id: 
        //     });
        //     el2.skillRequirementsById.forEach(el3 => {
        //       // console.log(el3.skillBySkillId.title);

        //     });
        //   });
        // });
        for (let i = 0; i < this.skillList.length; i++) {
          for (let y = 0; y < this.skillList[i].length; y++) {

          }
          this.test.push({

          });
        }
        console.log(this.test);
      },
      err => {

      }
    );
  }

  navigateProject(id) {
    this.router.navigate(['manager/project/info'], { queryParams: { "id": id } });
  }
}

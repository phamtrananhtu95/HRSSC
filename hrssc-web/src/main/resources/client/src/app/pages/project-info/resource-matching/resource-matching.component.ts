import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ProjectService } from '../../../services/project.service';
import { ResourceMatch } from '../../../models/resourceMatched.model';

@Component({
  selector: 'app-resource-matching',
  templateUrl: './resource-matching.component.html',
  styleUrls: ['./resource-matching.component.css']
})
export class ResourceMatchingComponent implements OnInit {

  public listResourceMatch = new Array<ResourceMatch>();


  constructor(
    private route: ActivatedRoute,
    private auth: AuthenticateService,
    private prjService: ProjectService
  ) { }

  ngOnInit() {
    if (this.auth.checkLogin()) {
      this.getResourceMatching();
    }
  }

  getResourceMatching() {
    let projectId = this.route.snapshot.queryParams['id'];
    let userId = this.auth.getUserId();

    this.prjService.getResourceMatching(userId, projectId).subscribe(
      res => {
        this.listResourceMatch = res;
        this.listResourceMatch.forEach(element => {
          element.listSkill = "";
          element.humanResourceByHumanResourceId.resourceSkillsById.forEach(el => {
              element.listSkill = element.listSkill + el.skillBySkillId.title + ", ";
          });
        });
        console.log(this.listResourceMatch);
      },
      err => {
      }
    );
  }


}

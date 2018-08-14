import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
    private prjService: ProjectService,
    private router: Router
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

        console.log(this.listResourceMatch);
        
        this.listResourceMatch.forEach(element => {
          element.humanResourceByHumanResourceId.availableDate = this.ConvertToDatetime(element.humanResourceByHumanResourceId.availableDate);
          element.humanResourceByHumanResourceId.availableDuration = this.ConvertToDatetime(element.humanResourceByHumanResourceId.availableDuration);
          element.listSkill = "";
          element.humanResourceByHumanResourceId.resourceSkillsById.forEach(el => {
              element.listSkill = element.listSkill + el.skillBySkillId.title + ", ";
          });

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

  navigateResource(id){
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": id}});
  }

  viewCompanyDetail(companyId) {
    this.router.navigate(['company/info'], {queryParams:{"id": companyId}});
  }
}

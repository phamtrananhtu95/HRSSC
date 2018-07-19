import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ApplianceService } from '../../../services/appliance.service';
import { Appliable, Project } from '../../../models';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../../services/project.service';

@Component({
  selector: 'app-project-info-popup',
  templateUrl: './project-info-popup.component.html',
  styleUrls: ['./project-info-popup.component.css']
})
export class ProjectInfoPopupComponent implements OnInit {
  public userId: number;
  public projectId: number;
  public project = new Project();
  public resourceAppliance: Appliable;
  @Input() listAvailableResource;

  constructor(
    private route: ActivatedRoute,
    private empService: EmployeeService,
    private auth: AuthenticateService,
    private applyService: ApplianceService,
    private prjService: ProjectService,
    private router: Router
  ) { }

  ngOnInit() {
    this.userId = this.auth.getUserId();
    this.projectId = this.route.snapshot.queryParams['id'];

  }
  ngOnChanges() {
    if (this.listAvailableResource) {
      // console.log(this.listAvailableResource);
    }
  }



  getAvailableResource() {
    this.prjService.getProjectByProjectId(this.userId, this.projectId).subscribe(
      res => {
        this.project = res;
      },
      err => {
      }
    );
    this.empService.getAppliableByManagerId(this.userId, this.projectId).subscribe(
      res => {
        this.listAvailableResource = [];
        res.forEach(el => {
          this.listAvailableResource.push({
            project: this.project,
            resource: el
          })
        });
      },
      err => {

      }
    );
    // console.log("----------" + JSON.stringify(this.listAvailableResource));

  }
  appliable(val: any) {
    console.log(val);
    this.resourceAppliance = new Appliable();
    this.resourceAppliance.projectId = val.project.id;
    this.resourceAppliance.humanResourceId = val.resource.id;
    this.applyService.applianceResource(this.resourceAppliance).subscribe(
      res => {
        this.getAvailableResource();
      },
      err => {

      }
    );

  }

  viewContract(humanResourceId, projectId) {
    (<any>$("#modal_theme_info")).modal("hide");
    this.router.navigate(['job/contract'], {
      queryParams:
        {
          "humanResourceId": humanResourceId,
          "projectId": projectId,
          "composeContract": true
        }
    });
  }

}

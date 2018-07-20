import { Component, OnInit } from '@angular/core';
import { ApplianceService } from '../../services/appliance.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { AcceptResource } from '../../models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-appliances',
  templateUrl: './manage-appliances.component.html',
  styleUrls: ['./manage-appliances.component.css']
})
export class ManageAppliancesComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Appliance";
  public userId: number;
  public listAppliance = [];
  public acceptResource: AcceptResource;

  constructor(
    private applyService: ApplianceService,
    private auth: AuthenticateService,
    private router: Router
  ) { }

  ngOnInit() {
    this.userId = this.auth.getUserId();
    this.getAllAppliance();
  }

  getAllAppliance() {
    this.applyService.getAllAppliance(this.userId).subscribe(
      res => {
        this.listAppliance = [];
        this.listAppliance = res;
        this.listAppliance.forEach(el => {
          el.countApply = el.interactionsById.length;
        });

      },
      err => {

      }
    );
  }

  selectResource(resource, projectId) {
    this.acceptResource = new AcceptResource();
    this.acceptResource.id = resource.id;
    this.acceptResource.humanResourceId = resource.humanResourceId;
    this.acceptResource.projectId = projectId;

    this.applyService.acceptResource(this.acceptResource).subscribe(
      res => {
        this.getAllAppliance();
      },
      err => {
      }
    );

    // console.log(this.acceptResource);
  }
  

  linkToContract(interactionsId) {
    debugger;
    this.router.navigate(['job/contract'], {
      queryParams:
        {
          "interactionsId": interactionsId,
          "composeContract": false
        }
    });
  }
}

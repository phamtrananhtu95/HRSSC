import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ApplianceService } from '../../../services/appliance.service';
import { Appliable } from '../../../models';

@Component({
  selector: 'app-project-info-popup',
  templateUrl: './project-info-popup.component.html',
  styleUrls: ['./project-info-popup.component.css']
})
export class ProjectInfoPopupComponent implements OnInit {
  public userId: number;
  public resourceAppliance: Appliable;
  @Input() listAvailableResource;

  constructor(
    private empService: EmployeeService,
    private auth: AuthenticateService,
    private applyService: ApplianceService
  ) { }

  ngOnInit() {
    this.userId = this.auth.getUserId();
  }
  ngOnChanges() {
    if (this.listAvailableResource) {
        // console.log(this.listAvailableResource);
    }
  }
  appliable(val: any){
      console.log(val);
      this.resourceAppliance = new Appliable();
      this.resourceAppliance.projectId = val.project.id;
      this.resourceAppliance.humanResourceId = val.resource.id;
      this.applyService.applianceResource(this.resourceAppliance).subscribe(
        res => {
            
        },
        err => {

        }
      );

  }

}

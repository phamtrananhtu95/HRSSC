import { Component, OnInit } from '@angular/core';
import { ManagementService } from '../../services/management.service';
import { User } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { Manager } from '../../models/manager.model';

@Component({
  selector: 'app-chef-manage-account-manager',
  templateUrl: './chef-manage-account-manager.component.html',
  styleUrls: ['./chef-manage-account-manager.component.css']
})
export class ChefManageAccountManagerComponent implements OnInit {

  public managers: Manager[];
  public companyId;
  constructor(
    private managementService: ManagementService,
    private authenticateService: AuthenticateService
  ) { }

  ngOnInit() {
    this.companyId = this.authenticateService.getCompanyId();
    this.getManagersByCompanyId();
  }

  getManagersByCompanyId() {
    this.managementService.getManagersByCompanyId(this.companyId).subscribe(
      res => {
        this.managers = res;
        console.log(this.managers);        
      },
      err => {
        console.log(err);
      }
    )
  }

}

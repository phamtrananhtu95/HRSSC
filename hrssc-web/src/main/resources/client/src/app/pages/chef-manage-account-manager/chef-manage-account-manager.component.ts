import { Manager } from './../../models/manager.model';
import { Component, OnInit } from '@angular/core';
import { ManagementService } from '../../services/management.service';
import { User } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chef-manage-account-manager',
  templateUrl: './chef-manage-account-manager.component.html',
  styleUrls: ['./chef-manage-account-manager.component.css']
})
export class ChefManageAccountManagerComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Manage manager";
  public subTitle = " - Manager account";
  public titleLink = " / Manager account";

  // ===============================
  public managers: Manager[];
  public companyId;
  public isEditForm: boolean;
  public editManagerModel = new Manager();

  constructor(
    private managementService: ManagementService,
    private authenticateService: AuthenticateService,
    private router: Router
  ) { }

  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;
    this.companyId = this.authenticateService.getCompanyId();

    // this.companyId = 10;
    // this.managers = [];
    // let manager = new Manager();
    // manager.username = "tuhihi@gmail.com";
    // manager.fullname = "Tu hihi";
    // manager.email = "tuhihi@gmail.com";
    // manager.tel = "123456789";
    // manager.status = "2";
    // manager.projectNum = 6;
    // manager.resourceNum = 2;
    // this.managers.push(manager);

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

  addManager() {
    this.isEditForm = false;
    this.editManagerModel = new Manager();
    this.editManagerModel.companyId = this.companyId;
  }

  editManager(manager: Manager) {
    this.isEditForm = true;
    this.editManagerModel = manager;
    this.editManagerModel.companyId = this.companyId;
  }

  viewManagerDetail(id){
    // console.log(id);
    
    this.router.navigate(['chief/manage/manager-info'], { queryParams: { "id": id } });
  }
}

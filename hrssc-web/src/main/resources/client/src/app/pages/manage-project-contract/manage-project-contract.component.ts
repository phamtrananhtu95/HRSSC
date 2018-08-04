import { Component, OnInit } from '@angular/core';
import { ContractService } from '../../services/contract.service';
import { Router } from '@angular/router';
import { AuthenticateService } from '../../services/authenticate.service';

@Component({
  selector: 'app-manage-project-contract',
  templateUrl: './manage-project-contract.component.html',
  styleUrls: ['./manage-project-contract.component.css']
})
export class ManageProjectContractComponent implements OnInit {

  public parentTitle = "Home";
  public title = " - Manage project contract";
  // public subTitle = " - Resource";
  public titleLink = " / Project contract";
  public countContract = 0;

  // =============================

  public projectContracts: any[];
  public userId: number;

  constructor(
    private contractService: ContractService,
    private router: Router,
    private authen: AuthenticateService,
  ) {
    this.userId = this.authen.getUserId();
   }

  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;
    ////////////////////////////////


    this.loadProjectContract();
  }

  loadProjectContract() {
    this.contractService.loadProjectContract(this.userId).subscribe(
      res => {
        this.projectContracts = res;
      }
    );
  }

  viewContractDetail(jobId) {
    this.router.navigate(['manage/contract/view'], { queryParams: { "id": jobId } });
  }

}

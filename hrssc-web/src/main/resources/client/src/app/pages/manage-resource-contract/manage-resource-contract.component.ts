import { Component, OnInit } from '@angular/core';
import { ContractService } from '../../services/contract.service';
import { Router } from '@angular/router';
import { AuthenticateService } from '../../services/authenticate.service';

@Component({
  selector: 'app-manage-resource-contract',
  templateUrl: './manage-resource-contract.component.html',
  styleUrls: ['./manage-resource-contract.component.css']
})
export class ManageResourceContractComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Manage resource contract";
  // public subTitle = " - Resource";
  public titleLink = " / Resource contract";
  public countContract = 0;

  // =============================

  public resourceContracts: any[];
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


    this.loadResourceContract();
  }

  loadResourceContract() {
    this.contractService.loadResourceContract(this.userId).subscribe(
      res => {
        this.resourceContracts = res;
      }
    );
  }

  viewContractDetail(jobId) {
    this.router.navigate(['manage/contract/view'], { queryParams: { "id": jobId } });

    // this.contractService.loadContractInfo(jobId).subscribe(
    //   res => {
    //     this.router.navigate(['manager/contract/view'], { queryParams: { "id": jobId } });
    //   }
    // )
  }

}

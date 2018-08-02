import { Component, OnInit } from '@angular/core';
import { ContractService } from '../../services/contract.service';

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

  constructor(
    private contractService: ContractService,
  ) { }

  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;
    ////////////////////////////////


    this.loadResourceContract();
  }

  loadResourceContract() {
    this.contractService.loadResourceContract(16).subscribe(
      res => {
        this.resourceContracts = res;
      }
    );
  }

}

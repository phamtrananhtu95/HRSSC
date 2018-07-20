import { Component, OnInit } from '@angular/core';
import { ApplianceService } from '../../../services/appliance.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-invitation-contract',
  templateUrl: './invitation-contract.component.html'
})
export class InvitationContractComponent implements OnInit {

  public listContractResource = [];
  public userId: number;
  constructor(
    private applianceService: ApplianceService,
    private authenticateService: AuthenticateService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.userId = this.authenticateService.getUserId();
    this.loadAllContractProject();
  }

  loadAllContractProject() {
    this.applianceService.loadHumanContract(this.userId).subscribe(
      res => {
        this.listContractResource = [];
        this.listContractResource = res;
      },
      err => {
        console.log(err);
      }
    );
  }

  linkToContract(interactionsId) {
    this.router.navigate(['job/contract'], {
      queryParams:
        {
          "interactionsId": interactionsId,
          "composeContract": false
        }
    });
  }

  // acceptOffer(contractId) {
  //   this.formContract.id = contractId;
  //   this.formContract.latestEditorId = this.userId;
  //   this.invitationService.acceptOffer(this.formContract).subscribe(
  //     res => {

  //     }
  //   );
  // }
}

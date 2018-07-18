import { Component, OnInit } from '@angular/core';
import { InvitationService } from '../../../services/invitation.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { Router } from '@angular/router';
import { Interaction, ContractByContractId } from '../../../models/interaction.model';

@Component({
  selector: 'app-appliance-contract',
  templateUrl: './appliance-contract.component.html',
  styleUrls: ['./appliance-contract.component.css']
})
export class ApplianceContractComponent implements OnInit {

  public listProject = [];
  public userId: number;
  public formContract = new ContractByContractId();
  constructor(
    private invitationService: InvitationService,
    private authenticateService: AuthenticateService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.userId = this.authenticateService.getUserId();
    this.loadAllContractProject();
  }

  loadAllContractProject() {
    this.invitationService.loadProjectContract(this.userId).subscribe(
      res => {
        this.listProject = [];
        this.listProject = res;
      },
      err => {
        console.log(err);
      }
    );
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

  // acceptOffer(contractId) {
  //   this.formContract.id = contractId;
  //   this.formContract.latestEditorId = this.userId;
  //   this.invitationService.acceptOffer(this.formContract).subscribe(
  //     res => {

  //     }
  //   );
  // }
}

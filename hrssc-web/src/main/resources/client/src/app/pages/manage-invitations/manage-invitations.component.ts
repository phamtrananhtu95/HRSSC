import { Component, OnInit } from '@angular/core';
import { InvitationService } from '../../services/invitation.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Invitation } from '../../models/invitation.model';
import { Interaction } from '../../models/interaction.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-invitations',
  templateUrl: './manage-invitations.component.html',
  styleUrls: ['./manage-invitations.component.css']
})
export class ManageInvitationsComponent implements OnInit {

  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Invitations";

  public titleLink = " / Invitation";  
  // --------------------------------

  public userId: number;
  public invitations: Invitation[];
  public formModel = new Interaction();

  // public countInvite: number;

  constructor(
    private invitationService: InvitationService,
    private authenticateService: AuthenticateService,
    private router: Router
  ) {
    this.userId = this.authenticateService.getUserId();
  }

  ngOnInit() {
    this.userId = this.authenticateService.getUserId();
    this.getInvitations();
  }

  getInvitations() {
    this.invitationService.getInvites(this.userId).subscribe(
      res => {

        this.invitations = res;
        this.invitations = this.invitations.filter(invitation =>
          invitation.interactionsById && invitation.interactionsById.length > 0
        );
        this.invitations.forEach(invite => {
          invite.countInvite = invite.interactionsById.length;
        });
        // console.log("----------" + JSON.stringify(this.invitations));

      },
      err => {
        console.log(err);
      }
    )
  }

  acceptInvite(projectId, humanResourceId) {
    this.formModel.projectId = projectId;
    this.formModel.humanResourceId = humanResourceId;
    this.invitationService.acceptInvite(this.formModel).subscribe(
      res => {
        this.getInvitations();
      },
      err => {
        console.log(err);
      }
    )
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

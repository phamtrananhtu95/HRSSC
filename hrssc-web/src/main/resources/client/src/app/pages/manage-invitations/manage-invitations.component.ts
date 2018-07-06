import { Component, OnInit } from '@angular/core';
import { InvitationService } from '../../services/invitation.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { Invitation } from '../../models/invitation.model';

@Component({
  selector: 'app-manage-invitations',
  templateUrl: './manage-invitations.component.html',
  styleUrls: ['./manage-invitations.component.css']
})
export class ManageInvitationsComponent implements OnInit {

  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Invite";
  // --------------------------------

  public managerId: number;
  public invitations: Invitation[];
  // public countInvite: number;
  public a: number;

  constructor(
    private invitationService: InvitationService,
    private authenticateService: AuthenticateService
  ) { }

  ngOnInit() {
    this.managerId = this.authenticateService.getUserId();
    this.getInvitations();
  }

  getInvitations() {
    this.invitationService.getInvites(this.managerId).subscribe(
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

}

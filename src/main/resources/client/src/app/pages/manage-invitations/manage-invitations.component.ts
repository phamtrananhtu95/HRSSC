import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-invitations',
  templateUrl: './manage-invitations.component.html',
  styleUrls: ['./manage-invitations.component.css']
})
export class ManageInvitationsComponent implements OnInit {
  
  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Invite";

  constructor() { }

  ngOnInit() {
  }

}

import { Component, OnInit } from '@angular/core';
import { User, UserList } from '../../models';

@Component({
  selector: 'app-manage-resources',
  templateUrl: './manage-resources.component.html',
  styleUrls: ['./manage-resources.component.css']
})
export class ManageResourcesComponent implements OnInit {
  public resource: User[]

  constructor() { }

  ngOnInit() {
    this.resource = new UserList().users;
  }

}

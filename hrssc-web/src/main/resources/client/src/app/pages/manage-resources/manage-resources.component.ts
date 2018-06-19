import { Component, OnInit } from '@angular/core';
import { User } from '../../models';

@Component({
  selector: 'app-manage-resources',
  templateUrl: './manage-resources.component.html',
  styleUrls: ['./manage-resources.component.css']
})
export class ManageResourcesComponent implements OnInit {
  // public resources: User[]
  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Resource";
  
  constructor() { }

  ngOnInit() {
    // title:""
    // this.resources = new UserList().users;
    // this.titleService.setTile("Home","Manager resource","Resource");
  }

}

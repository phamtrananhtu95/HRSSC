import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manage-appliances',
  templateUrl: './manage-appliances.component.html',
  styleUrls: ['./manage-appliances.component.css']
})
export class ManageAppliancesComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Appliance";
  constructor() { }

  ngOnInit() {
  }

}

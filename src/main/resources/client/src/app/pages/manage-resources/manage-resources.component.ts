import { Component, OnInit } from '@angular/core';
import { User, UserList } from '../../models';

import { EmployeeService } from '../../services/employee.service';
import { resource } from 'selenium-webdriver/http';

@Component({
  selector: 'app-manage-resources',
  templateUrl: './manage-resources.component.html',
  styleUrls: ['./manage-resources.component.css']
})
export class ManageResourcesComponent implements OnInit {
  public resources: User[]

  constructor(private _employeeService:EmployeeService) {
   }

  ngOnInit() {
    // this.resources = new UserList().users;
    this._employeeService.getEmployees().subscribe((resources)=> {
      console.log(resources);
      this.resources = resources;
    },(error)=>{
      console.log(error);
    });
  }

}

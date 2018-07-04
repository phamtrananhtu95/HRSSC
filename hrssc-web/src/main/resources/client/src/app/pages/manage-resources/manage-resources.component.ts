import { Component, OnInit } from '@angular/core';
import { User, Employee } from '../../models';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';

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
  
  public humanResource: Employee[];
  public managerId;
  public resourceId;
  
  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    private router: Router
  ) { }
  
  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;

    this.managerId = this.authenticateService.getUserId();
    this.getHumanResourceByManagerId();

    // (<any>window).formSelect2 = true;
    // (<any>window).interactionsMin = true;
    

  }

  getHumanResourceByManagerId(){
    this.employeeService.getHumanResourceByManagerId(this.managerId).subscribe(
      res => {
        this.humanResource = res;
        console.log(this.humanResource);
      },
      error => {
        console.log(error);
      }
    )
  }

  viewHumanResourceDetail(humanResourceId) {
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": humanResourceId}});
  }
}

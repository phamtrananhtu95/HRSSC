import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../../models';

@Component({
  selector: 'app-resource-info',
  templateUrl: './resource-info.component.html',
  styleUrls: ['./resource-info.component.css']
})
export class ResourceInfoComponent implements OnInit {
  public humanResource: Employee;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private employeeService: EmployeeService
  ) { }

  ngOnInit() {
    let humanResourceId = this.route.snapshot.queryParams['id']
    this.employeeService.getHumanResourceById(humanResourceId).subscribe(
      res => {
        this.humanResource = res;
      },
      err => {
        
      }
    )
  }

}

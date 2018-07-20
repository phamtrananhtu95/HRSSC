import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { ResourceHistory } from '../../../models';

@Component({
  selector: 'app-history-project',
  templateUrl: './history-project.component.html',
  styleUrls: ['./history-project.component.css']
})
export class HistoryProjectComponent implements OnInit {

  public resourceHistories: ResourceHistory[];

  constructor(
    private employeeService: EmployeeService,
  ) { }

  ngOnInit() {
    this.employeeService.loadHistoryResource(38).subscribe(
      res => {
        this.resourceHistories = res;
      },
      err => {
        console.log(err);
      }
    );
  }

}

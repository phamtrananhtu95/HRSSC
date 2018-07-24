import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { ResourceHistory, Employee } from '../../../models';

@Component({
  selector: 'app-history-project',
  templateUrl: './history-project.component.html',
  styleUrls: ['./history-project.component.css']
})
export class HistoryProjectComponent implements OnInit {

  @Input() resourceId: number; 
  public resourceHistories: ResourceHistory[];
  public resourceReviews: any;

  constructor(
    private employeeService: EmployeeService,
  ) {
    
   }

  ngOnInit() {
    // console.log("tui ne" + this.resourceId)
    this.employeeService.loadReviewsResource(this.resourceId).subscribe(
      res => {
        this.resourceReviews = res;
        console.log("aaaaa: " + JSON.stringify(this.resourceReviews));

      //   this.resourceHistories.forEach(resourceHistory => {
      //     resourceHistory.feedbacksById.forEach(feedbackById => {
      //         console.log(feedbackById.userByUserId.fullname);
      //     });
      // });
        // console.log("-----" + JSON.stringify(this.resourceHistories));
      },
      err => {
        console.log(err);
      }
    );

    
  }

}

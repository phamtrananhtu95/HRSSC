import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-contract',
  templateUrl: './view-contract.component.html',
  styleUrls: ['./view-contract.component.css']
})
export class ViewContractComponent implements OnInit {

  public jobId: number;

  constructor(
    private route: ActivatedRoute,

  ) {
    this.route.queryParams.subscribe(param => {
      this.jobId = this.route.snapshot.queryParams['id'];
    });
  }

  ngOnInit() {
    console.log("aaaaaaaa" + this.jobId);
  }

}

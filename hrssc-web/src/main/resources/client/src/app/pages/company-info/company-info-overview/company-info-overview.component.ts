import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-company-info-overview',
  templateUrl: './company-info-overview.component.html',
  styleUrls: ['./company-info-overview.component.css']
})
export class CompanyInfoOverviewComponent implements OnInit {
  @Input() company;
  @Input() openProjects;
  @Input() resourceAvailable;
  public projectAvailable: any;
  public resourceAvai: any;
  public companyInfo: any;
  constructor(
    private router: Router
  ) { }

  ngOnInit() {
  }

  ngOnChanges() {
    if(this.openProjects){
      this.projectAvailable = this.openProjects;
    }
    if(this.resourceAvailable){
      this.resourceAvai = this.resourceAvailable;
    }
    if(this.company) {
      this.companyInfo = this.company;
    }
  }

  viewProjectDetail(id) {
    this.router.navigate(['manager/project/info'], {queryParams:{"id": id}});
  }
  viewResourceDetail(id) {
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": id}});
  }
}

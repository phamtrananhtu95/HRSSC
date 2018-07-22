import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Search } from '../../models/search.model';
import { SearchService } from '../../services/search.service';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {
  public searchResourceModel = new Search();
  public listResourceSearch = [];
  public isResourceSearch: boolean;
  public countResult: number;

  public searchProjectModel = new Search();
  public listProjectSearch = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private searchService: SearchService
  ) {
    this.route.queryParams.subscribe(params => {
      if (!params) {
        return;
      }
      // resource
      this.searchResourceModel.userId = params.RuserId;
      this.searchResourceModel.company = params.Rcompany;
      this.searchResourceModel.skill = params.Rskill;
      this.searchResourceModel.location = params.Rlocation;

      // project
      this.searchProjectModel.company = params.Pcompany;
      this.searchProjectModel.userId = params.PuserId;
      this.searchProjectModel.skill = params.Pskill;
      this.searchProjectModel.location = params.Plocation;

    })
  }

  ngOnInit() {
    this.searchResource();
    this.searchProject();
  }
  searchResource() {
   
    if (this.searchResourceModel) {
      this.searchService.searchByResource(this.searchResourceModel).subscribe(
        res => {
          this.isResourceSearch = true;
          this.listResourceSearch = res;
          console.log(this.listResourceSearch);

          this.countResult = this.listResourceSearch.length;
        },
        err => {

        }
      );
    }

  }

  searchProject() {
    if (this.searchProjectModel) {
      this.searchService.searchByProject(this.searchProjectModel).subscribe(
        res => {
          this.isResourceSearch = false;
          this.listProjectSearch = res;
          this.countResult = this.listProjectSearch.length;
        },
        err => {

        }
      );
    }

  }
}

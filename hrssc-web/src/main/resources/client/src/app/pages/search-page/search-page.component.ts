import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Search } from '../../models/search.model';
import { SearchService } from '../../services/search.service';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {
  public searchResourceModel = new Search();
  public listResourceSearch = [];
  public isResourceSearch: boolean;
  public countResource: number;
  public countProject: number;

  public searchProjectModel = new Search();
  public listProjectSearch = [];

  public searchModel = new Search();
  public listSkillOpt = [];
  public listLocationOpt = [
    {
      value: "Ho Chi Minh",
      label: "Ho Chi Minh"
    },
    {
      value: "Ha Noi",
      label: "Ha Noi"
    },
    {
      value: "Da Nang",
      label: "Da Nang"
    },
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private searchService: SearchService,
    private employeeService: EmployeeService
  ) {
    this.route.queryParams.subscribe(params => {
      if (!params) {
        return;
      }
      // search model
      this.searchModel.userId = params.userId;
      this.searchModel.location = params.location;
      this.searchModel.skill = params.skill;
      this.searchModel.company = params.company;

      

      // resource
      this.searchResourceModel.userId = params.userId;
      this.searchResourceModel.company = params.company;
      this.searchResourceModel.skill = params.skill;
      this.searchResourceModel.location = params.location;

      // project
      this.searchProjectModel.company = params.company;
      this.searchProjectModel.userId = params.userId;
      this.searchProjectModel.skill = params.skill;
      this.searchProjectModel.location = params.location;

    })
  }

  ngOnInit() {
    this.searchResource(this.searchResourceModel);
    this.searchProject(this.searchProjectModel);
    this.getSkillList();
  }
  search() {
    this.searchResource(this.searchModel);
    this.searchProject(this.searchModel);
  }

  searchResource(model: any) {
   
    if (this.searchResourceModel) {
      this.searchService.searchByResource(model).subscribe(
        res => {
          this.isResourceSearch = true;
          this.listResourceSearch = res;
          this.countResource = this.listResourceSearch.length;
        },
        err => {

        }
      );
    }

  }

  searchProject(model: any) {
    if (this.searchProjectModel) {
      this.searchService.searchByProject(model).subscribe(
        res => {
          this.isResourceSearch = false;
          this.listProjectSearch = res;
          this.countProject = this.listProjectSearch.length;
        },
        err => {

        }
      );
    }

  }
  getSkillList(){
    this.employeeService.getSkills().subscribe(
      res => {
        this.listSkillOpt = [];
        res.forEach(el => {
          this.listSkillOpt.push({ value: el.title, label: el.title });
        });

      }
    );
  }
}

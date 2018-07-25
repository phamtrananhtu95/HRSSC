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

  public viewProjectModel:any;

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
    
    if(this.searchModel.skill == undefined){
      this.searchModel.skill = "";
    }
    if(this.searchModel.location == undefined) {
      this.searchModel.location = "";
    }
    this.searchResource(this.searchModel);
    this.searchProject(this.searchModel);
  }

  searchResource(model: any) {
   
    if (this.searchResourceModel) {
      this.searchService.searchByResource(model).subscribe(
        res => {
          
          this.listResourceSearch = res;

          this.countResource = this.listResourceSearch.length;
        },
        err => {

        }
      );
    }

  }
  viewDetailResource(id){
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": id}});
  }

  searchProject(model: any) {
    if (this.searchProjectModel) {
      this.searchService.searchByProject(model).subscribe(
        res => {
          console.log(res);
          this.listProjectSearch = res;
          this.viewProjectModel = this.listProjectSearch[0];
          this.countProject = this.listProjectSearch.length;
        },
        err => {

        }
      );
    }
  }
  viewProject(project) {

    this.viewProjectModel = project;
    console.log(this.viewProjectModel);
    
  }
  viewDetailProject(projectId){
    this.router.navigate(['manager/project/info'], {queryParams:{"id": projectId}});
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

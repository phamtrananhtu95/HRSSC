import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Search } from '../../models/search.model';
import { SearchService } from '../../services/search.service';
import { EmployeeService } from '../../services/employee.service';
declare var $: any;
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
  public skillList: any;

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
    console.log(this.countProject);
    
    // this.showAlertSearch(this.countResource, this.countProject);
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

  showAlertSearch(countResource, countPrj){
    var s = countResource +"resources,"+ countPrj +" projects found";
    $.jGrowl(s, {
      // animateOpen: {
      //   opacity: 'show' 
      // },
      theme: 'bg-success',
      position: 'bottom-right',
      themeState: 'highlight'
      
    });
  }

  searchResource(model: any) {
   
    if (this.searchResourceModel) {
      this.searchService.searchByResource(model).subscribe(
        res => {
          
          this.listResourceSearch = res;
          console.log(this.listResourceSearch);
          
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
          this.skillList = "";
          console.log(res);
          
          this.listProjectSearch = res;
          this.listProjectSearch[0].projectRequirementsById.forEach(el => {
            el.skillRequirementsById.forEach(el2 => {
              this.skillList = this.skillList + el2.skillBySkillId.title + ", ";
            });
          });
          var lastIndex = this.skillList.lastIndexOf(", ");
          this.skillList = this.skillList.substring(0, lastIndex);

          console.log(this.skillList);
          

          this.listProjectSearch[0].createDate = this.ConvertToDatetime(this.listProjectSearch[0].createDate);
          this.listProjectSearch[0].endDate = this.ConvertToDatetime(this.listProjectSearch[0].endDate);
          this.viewProjectModel = this.listProjectSearch[0];
          this.countProject = this.listProjectSearch.length;
        },
        err => {

        }
      );
    }
  }
  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    return dateParse;
  }
  viewProject(project) {
    project.createDate = this.ConvertToDatetime(project.createDate);
    project.endDate = this.ConvertToDatetime(project.endDate);
    this.skillList = "";
    project.projectRequirementsById.forEach(el => {
      el.skillRequirementsById.forEach(el2 => {
        this.skillList = this.skillList + el2.skillBySkillId.title + ", ";
      });
    });
    var lastIndex = this.skillList.lastIndexOf(", ");
    this.skillList = this.skillList.substring(0, lastIndex);
    this.viewProjectModel = project;
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
  viewCompanyDetail(companyId) {
    this.router.navigate(['company/info'], {queryParams:{"id": companyId}});
  }
}

import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from '../../services/authenticate.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CompaniesService } from '../../services/companies.service';
import { Company } from '../../models';

@Component({
  selector: 'app-company-info',
  templateUrl: './company-info.component.html',
  styleUrls: ['./company-info.component.css']
})
export class CompanyInfoComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Company info";
  public titleLink = " / Company info";

  // ==========================
  public companyId: number;
  public userId: number;
  public companyName: any;
  public company;
  public openProjects = [];
  public resourceAvailable = [];
  public isChief: boolean;
  public countProject: number;
  public countResource: number;

  constructor(
    private auth: AuthenticateService,
    private route: ActivatedRoute,
    private companyService: CompaniesService,
    private router: Router

  ) {
    this.route.queryParams.subscribe(param => {
      this.companyId = this.route.snapshot.queryParams['id'];
      if(this.companyId == undefined){
          this.companyId = this.auth.getCompanyId();   
          this.isChief = true;
      }
      if(this.companyId == this.auth.getCompanyId()){
        this.isChief = true;
      }else{
        this.isChief = false;
      }
      this.userId = this.auth.getUserId();
      console.log(this.auth.getCompanyId());
      
      this.getCompanyInfo();
      this.getOpeningProject(this.userId, this.companyId);
      this.getAvailableResource(this.userId, this.companyId);
    });
   }

  ngOnInit() {
    // if (this.auth.checkLogin()) {
    //   this.companyId = this.route.snapshot.queryParams['id'];
    //   if(this.companyId == undefined){
    //       this.companyId = this.auth.getCompanyId();        
    //   }
    //   this.userId = this.auth.getUserId();
    //   this.getCompanyInfo();
    //   this.getOpeningProject(this.userId, this.companyId);
    // }
  }

  getCompanyInfo(){
    this.company = new Company();
    this.companyService.getCompanyInfoById(this.companyId).subscribe(
      res => {
          this.company = res;
      },
      err => {

      }
    );
  }
  getOpeningProject(userId, companyId){
    this.companyService.getOpeningProject(userId, companyId).subscribe(
      res => {
        this.openProjects = res;
        this.openProjects.forEach(element => {
          element.createDate = this.ConvertToDatetime(element.createDate);
          element.endDate = this.ConvertToDatetime(element.endDate);
        });
        this.countProject = this.openProjects.length;
      },
      err => {

      }
    );
  }

  getAvailableResource(userId, companyId) {
    this.companyService.getAvailableResource(userId, companyId).subscribe(
      res => {
         this.resourceAvailable = res;
         this.countResource = this.resourceAvailable.length;
      },
      err => {

      }
    );
  }
  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    return dateParse;
  }
  viewResourceDetail(id){
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": id}});
  }
  viewProjectDetail(id){
    this.router.navigate(['manager/project/info'], {queryParams:{"id": id}});
  }
}

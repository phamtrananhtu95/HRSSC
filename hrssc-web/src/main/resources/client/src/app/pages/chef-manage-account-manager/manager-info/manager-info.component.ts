import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ManagementService } from '../../../services/management.service';

@Component({
  selector: 'app-manager-info',
  templateUrl: './manager-info.component.html',
  styleUrls: ['./manager-info.component.css']
})
export class ManagerInfoComponent implements OnInit {
  public parentTitle = "Home";
  public title = " - Manager info";
  public titleLink = " / Manager info";

  public managerId: number;
  public manager: any;
  public listProject= [];
  public countProject:number;
  public listResource = [];
  public countResource:number;

  constructor(
    private auth: AuthenticateService,
    private route: ActivatedRoute,
    private router: Router,
    private managerService: ManagementService
  ) { 
    this.route.queryParams.subscribe(param => {
      this.managerId = this.route.snapshot.queryParams['id'];
      this.getManagerInfo();
      this.getListProjectByManagerId();
      this.getListResourceByManagerId();
    });
  }

  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;
  }

  getManagerInfo() {
    this.manager = [];
    this.managerService.getManagerById(this.managerId).subscribe(
      res => {
        this.manager = res;
        console.log(this.manager);
        
      },
      err => {

      }
    );
  }

  getListProjectByManagerId() {
    this.listProject = [];
    this.managerService.getListProjectByManagerId(this.managerId).subscribe(
      res => {
        this.listProject = res;
        this.listProject.forEach(element => {
          element.createDate = this.ConvertToDatetime(element.createDate);
          element.endDate = this.ConvertToDatetime(element.endDate);
        });
        
        this.countProject = this.listProject.length;
        
      },
      err => {

      }
    );
  }
  viewProjectDetail(id) {
    this.router.navigate(['manager/project/info'], { queryParams: { "id": id } });
  }

  getListResourceByManagerId(){
     this.listResource = [];
     this.managerService.getListResourceByManagerId(this.managerId).subscribe(
       res => {
          this.listResource = res;
          this.listResource.forEach(element => {
            element.availableDate = this.ConvertToDatetime(element.availableDate);
            element.availableDuration = this.ConvertToDatetime(element.availableDuration);
          });
          this.countResource = this.listResource.length;
          
       },
       err => {}
     );
  }
  viewHumanResourceDetail(humanResourceId){
    this.router.navigate(['manager/resource/info'], {queryParams:{"id": humanResourceId}});
  }

  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
    return dateParse;
  }

}

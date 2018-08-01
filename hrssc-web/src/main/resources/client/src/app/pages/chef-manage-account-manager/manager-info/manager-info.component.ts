import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ActivatedRoute } from '@angular/router';
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

  constructor(
    private auth: AuthenticateService,
    private route: ActivatedRoute,
    private managerService: ManagementService
  ) { 
    this.route.queryParams.subscribe(param => {
      this.managerId = this.route.snapshot.queryParams['id'];
      this.getManagerInfo();
    });
  }

  ngOnInit() {
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
}

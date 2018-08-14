import { Component, OnInit, ViewChild } from '@angular/core';
import { User, Employee, SkillBySkillId } from '../../models';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { AuthenticateService } from '../../services/authenticate.service';
import { ResourceManagerPopoverComponent } from './resource-manager-popover.component';
import swal from 'sweetalert';

@Component({
  selector: 'app-manage-resources',
  templateUrl: './manage-resources.component.html',
  styleUrls: ['./manage-resources.component.css']
})
export class ManageResourcesComponent implements OnInit {
  @ViewChild(ResourceManagerPopoverComponent) resourceManagerPopoverComponent: ResourceManagerPopoverComponent;

  // public resources: User[]
  public parentTitle = "Home";
  public title = " - Manage resources";
  public subTitle = " - Resource";
  public titleLink = " / Resources";


  public humanResource: Employee[];
  // public humanResource = new Array<Employee>();

  public managerId;
  public resourceId;

  constructor(
    private employeeService: EmployeeService,
    private authenticateService: AuthenticateService,
    private router: Router
  ) { }

  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;

    this.managerId = this.authenticateService.getUserId();
    this.getHumanResourceByManagerId();

    // (<any>window).formSelect2 = true;
    // (<any>window).interactionsMin = true;


  }

  getHumanResourceByManagerId() {
    this.employeeService.getHumanResourceByManagerId(this.managerId).subscribe(
      res => {

        // res.forEach(human => {
        //   if (human.id != -1) {
        //     this.humanResource.push(human)
        //   }
        // });

        this.humanResource = res;
        this.humanResource = this.humanResource.filter(human => human.status !== -1);
        // console.log("aaaaaaaaaaaaaaaa" + JSON.stringify(this.humanResource));
        this.bindSkill();
      },
      error => {
        console.log(error);
      }
    )
  }

  bindSkill() {
    this.humanResource.forEach(resource => {
      let skills = resource.resourceSkillsById;
      if (!skills || skills.length < 1) {
        return;
      }
      let skillList = "";
      for (let i = 0; i < skills.length - 1; i++) {
        skillList = skillList + skills[i].skillBySkillId.title + ", ";
      }
      skillList = skillList + skills[skills.length - 1].skillBySkillId.title;
      resource.skill = skillList;
    });
  }
  viewHumanResourceDetail(humanResourceId) {
    this.router.navigate(['manager/resource/info'], { queryParams: { "id": humanResourceId } });
  }



  removeHuman(humanId) {
    swal({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this resource!',
      icon: 'warning',
      buttons: ["Cancel", true],
      dangerMode: true,
    })
      .then((willDelete) => {
        if (willDelete) {
          swal("Delete success", {
            icon: "success",
          });
          this.employeeService.removeHuman(humanId).subscribe(
            res => {
              this.getHumanResourceByManagerId();
            }
          )
        }
      });

    // swal("Oops...", "Something went wrong!", "error");


  }
}

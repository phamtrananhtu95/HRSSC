import { Component, OnInit, ViewChild, ElementRef, Renderer2, Input } from '@angular/core';
import { Project, SkillRequirement, ProjectRequirement } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';
import { Router } from '@angular/router';
import { IMyDpOptions, IMyDateModel } from 'angular4-datepicker/src/my-date-picker';

declare var $: any;


@Component({
  selector: 'app-manage-projects',
  templateUrl: './manage-projects.component.html',
  styleUrls: ['./manage-projects.component.css']
})
export class ManageProjectsComponent implements OnInit {
  public myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'dd/mm/yyyy',
  };
  createDate: any = { date: { year: 2018, month: 10, day: 9 } };
  endDate: any = { date: { year: 2018, month: 10, day: 9 } };
  public parentTitle = "Home";
  public title = " - Manage project";
  public subTitle = " - Project";
  public titleLink = " / Project";

  // =================
  public projects;
  public positions;
  positionSelected: Number;
  isChecked = false;

  public positionList = [];
  public positionListShow = [];
  public userId;
  public companyId;
  public isUpdateForm: boolean;
  public isUpdatePositionForm: boolean;
  public formModel = new Project();
  public formPositionModel = new ProjectRequirement();

  // add something
  public positionOpt;
  public skillOpt;
  public listDomainOpt;
  public listTypeOpt;
  public isPositionUpdate: boolean;

  public countId = 0;
  public listDomain = [
    "Security", "Education", "Testing", "Game", "Language", "E-commerce", "Hardware Driver", "Communication", "Financial", "Transportation", "Communicating", "Government", "Management"
  ];

  public listType = [
    "Web Application", "Mobile Application", "Desktop Application", "Embedded Application", "Console Game Application"
  ];

  public myDatePickerOptionsStart: IMyDpOptions = {
    dateFormat: 'dd/mm/yyyy',
  };

  public myDatePickerOptionsEnd: IMyDpOptions = {
    dateFormat: 'dd/mm/yyyy',
  };

  constructor(
    private auth: AuthenticateService,
    private prjService: ProjectService,
    private router: Router
  ) { }

  ngOnInit() {
    (<any>window).datatables = true;
    (<any>window).select2 = true;
    (<any>window).datatablesBasic = true;

    this.setDisableUntilForStartDate();
    // this.projects = new projectList().projects;
    if (this.auth.checkLogin()) {
      // let userInfo = this.auth.getUserInfo();
      this.userId = this.auth.getUserId();
      this.companyId = this.auth.getCompanyId();
      this.getProjectsByCompanyId();
      this.loadAllPosition();

      this.formPositionModel.skillRequirementsById = [];
      this.formPositionModel.skillRequirementsById.push(new SkillRequirement());

      // 
      this.listDomainOpt = [];
      this.listDomain.forEach(domain => {
        this.listDomainOpt.push({ value: domain.toString(), label: domain })
      });

      // 
      this.listTypeOpt = [];
      this.listType.forEach(type => {
        this.listTypeOpt.push({ value: type.toString(), label: type })
      });
      // 
      // this.formPositionModel.positionId = '1';
      // if (this.formPositionModel.positionId) {
      //   this.loadSkillByPositionId(this.formPositionModel.positionId);
      //   this.formPositionModel.skillSelect = [
      //     15, 2
      //   ]
      // }
      // this.formPositionModel.quantity = 1;
      // this.formPositionModel.payment = 1;

    }
  }



  onDateChangedCreate(event: IMyDateModel) {
    this.formModel.createDate = event && event.jsdate ? event.jsdate.getTime() : null;
    let startDate = event.date;
    let optionsEnd = JSON.parse(JSON.stringify(this.myDatePickerOptionsEnd));
    optionsEnd.disableUntil = startDate;
    this.myDatePickerOptionsEnd = optionsEnd;

  }
  onDateChangedEnd(event: IMyDateModel) {
    this.formModel.endDate = event && event.jsdate ? event.jsdate.getTime() : null;
  }

  getProjectsByCompanyId() {
    this.prjService.getProjectByManagerId(this.userId).subscribe(
      res => {
        // console.log(res);
        this.projects = res;
        this.projects.forEach(element => {
          element.createDate = this.ConvertToDatetime(element.createDate);
          element.endDate = this.ConvertToDatetime(element.endDate);
        });
      },
      err => {
        console.log(err);
      }
    );
  }
  viewProjectDetail(id: any) {
    this.router.navigate(['manager/project/info'], { queryParams: { "id": id } });
  }

  loadAllPosition() {
    this.prjService.loadAllPosition().subscribe(
      res => {
        // this.positions = res;
        // this.positionSelected = 3;
        this.positionOpt = [];
        res.forEach(position => {
          this.positionOpt.push({ value: position.id.toString(), label: position.title })
        });
      },
      err => {
        console.log(err);
      }
    );
  }


  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    return dateParse;
  }
  loadSkillByPositionId(id) {
    this.prjService.loadSkillByPositionId(id).subscribe(
      res => {
        this.skillOpt = [];
        res.forEach(skill => {
          this.skillOpt.push({ value: skill.id.toString(), label: skill.title })
        });
      },
      err => {
        console.log(err);
      }
    );
  }
  onPositionSelected(val: any) {
    this.loadSkillByPositionId(val);
    this.formPositionModel.skillRequirementsById = [];
    this.formPositionModel.skillRequirementsById.push(new SkillRequirement());
  }
  createNewSkill() {
    this.formPositionModel.skillRequirementsById.push(new SkillRequirement());
  }

  // onSkillSelected(val: any) {
  //   val.forEach(val => {
  //     this.listSkillExp.forEach(el => {
  //       if (el.id === val) {
  //         var tmp = {
  //           skillId: el.id,
  //           title: el.title,
  //           positionId: el.positionId

  //         }
  //         this.formPositionModel.skillRequirementsById.push(tmp);
  //       }
  //     });
  //   });
  //   // console.log(this.formPositionModel.skillRequirementsById);

  // }

  selectSkill(val: any) {
    if (val.checked === true) {
      var tmp = "<div class='row' id='skill" + val.id + "'><div class='col-md-3'>" + val.value + "</div><div class='col-md-9'><label class='text-bold'>Experience</label><input type='text' id='" + val.id + "' name='skilExp[]'></div></div>";
      $('.item').append(tmp);
    }
    else if (val.checked === false) {
      $('#skill' + val.id).remove();
    }
  }

  addNewPosition() {
    this.positionOpt.forEach(pos => {
      if (pos.value == this.formPositionModel.positionId) {
        this.formPositionModel.positionTitle = pos.label;
      }
    });
    this.skillOpt.forEach(skils => {
      this.formPositionModel.skillRequirementsById.forEach(el => {
        if (el.skillId == skils.value) {
          el.skillTitle = skils.label;
        }
      });
    });
    // console.log(this.skillOpt);
    // console.log(this.formPositionModel.skillRequirementsById);
    // this.formPositionModel.skillRequirementsById.forEach(el => {
    //   el.skillTitle = "java"
    // });
    var projectRequirement = Object.assign({}, this.formPositionModel);

    // this.formPositionModel = Object.assign({}, val.value);
    this.positionList.push({
      id: this.countId,
      value: projectRequirement
    })
    this.countId = this.countId + 1;

    $('#quantityAdd').val('');
    $('#paymentAdd').val('');


    // ver 2

  }


  setDisableUntilForStartDate() {
    let now = new Date();
    let optionsStart = JSON.parse(JSON.stringify(this.myDatePickerOptionsStart));
    optionsStart.disableUntil = { year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate() };
    this.myDatePickerOptionsStart = optionsStart;
  }

  editPosition(val: any) {
    this.formPositionModel = Object.assign({}, val.value);
    this.loadSkillByPositionId(this.formPositionModel.positionId);

    this.positionList.forEach(el => {
      if (el.id === val.id) {
        el.value = this.formPositionModel;
      }
    });

    // console.log(this.positionList);
    // $("#positionAdd option[id='" + projectRequirement.positionId + "']").prop('selected', true);

  }
  deletePostion(val: any) {
    this.positionList = this.positionList.filter(function (el) {
      return el !== val;
    })
  }
  // add project
  addProject() {
    this.isUpdateForm = false;
    this.isUpdatePositionForm = false;
    this.formModel = new Project();
    this.positionList = [];
  }

  addNewProject() {
    this.formModel.domain = this.formModel.domain.toString();
    this.formModel.type = this.formModel.type.toString();

    this.formModel.userId = this.userId;
    this.formModel.companyId = this.companyId;

    // load list
    let tmp = new Array<ProjectRequirement>();
    this.positionList.forEach(el => {
      tmp.push(el.value);
    });
    this.formModel.projectRequirementsById = tmp;
    this.prjService.addProject(this.formModel).subscribe(
      res => {
        this.getProjectsByCompanyId();
        (<any>$("#modal_small")).modal("hide");
      },
      err => {
        console.log(err);
      }
    );
    console.log(this.formModel);
  }

  // update project
  updateProject(project: Project) {
    this.isUpdateForm = true;
    this.isUpdatePositionForm = true;
    this.formModel = Object.assign({}, project);
    this.positionList = project.projectRequirementsById;

    // this.formModel = project;
  }


  updatePrj() {
    this.prjService.updateProject(this.formModel).subscribe(
      res => {
        this.getProjectsByCompanyId();
        (<any>$("#modal_small")).modal("hide");
      },
      err => {
        console.log(err);
      }
    );
  }
}

import { Component, OnInit, Input, EventEmitter, Output, ViewContainerRef } from '@angular/core';
import { Project, ProjectRequirement } from '../../../models';
import { ProjectService } from '../../../services/project.service';
import { IMyDateModel, IMyDpOptions } from 'angular4-datepicker/src/my-date-picker';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Component({
  selector: 'app-info-project-manager',
  templateUrl: './info-project-manager.component.html',
  styleUrls: ['./info-project-manager.component.css']
})
export class InfoProjectManagerComponent implements OnInit {
  @Input() project: Project
  @Output() reloadMatchingResource: EventEmitter<any> = new EventEmitter();
  @Output() reloadProject: EventEmitter<any> = new EventEmitter();

  public projectInfo: Project;
  createDate: any = null;
  endDate: any = null;
  public positionList = [];
  public countId = 0;
  public formPositionModel = new ProjectRequirement();
  public positionOpt;
  public skillOpt;
  public listSkillExp;
  public isPositionUpdate: boolean;

  public status = null;

  public listSkill = [];

  // domain, type
  public listDomainOpt;
  public listTypeOpt;
  public listDomain = [
    "Security", "Education", "Testing", "Game", "Language", "E-commerce", "Hardware Driver", "Communication", "Financial", "Transportation", "Communicating", "Government", "Management"
  ];

  public listType = [
    "Web Application", "Mobile Application", "Desktop Application", "Embedded Application", "Console Game Application"
  ];
  public projectDomain: any;
  public projectType: any;


  public myDatePickerOptionsStart: IMyDpOptions = {
    dateFormat: 'dd/mm/yyyy',
  };
  public myDatePickerOptionsEnd: IMyDpOptions = {
    dateFormat: 'dd/mm/yyyy',
  };


  constructor(
    private prjService: ProjectService,
    private route: ActivatedRoute,
    private router: Router,
    public toastr: ToastsManager,
    public vcr: ViewContainerRef
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    // this.reloadLib();

    this.loadAllPosition();
    this.setDisableUntilForStartDate();

    this.listDomainOpt = [];
    this.listDomain.forEach(domain => {
      this.listDomainOpt.push({ value: domain.toString(), label: domain })
    });

    // 
    this.listTypeOpt = [];
    this.listType.forEach(type => {
      this.listTypeOpt.push({ value: type.toString(), label: type })
    });
    // this.formPositionModel.positionId = '1';

  }

  // reloadLib() {
  //   (<any>window).sweetAlertMin = true;
  //   (<any>window).componentModalsJs = true;
  // }

  ngOnChanges() {
    if (this.project) {
      this.projectInfo = Object.assign({}, this.project);

      if (JSON.stringify(this.projectInfo) != "{}") {
        this.projectDomain = this.projectInfo.domain.split(',');
        this.projectType = this.projectInfo.type.split(',');
      }
      
    //  set duration onchange
      this.projectInfo.duration = this.countDuration(this.projectInfo.endDate, this.projectInfo.createDate);

      this.createDate = this.ConvertToDatetime(this.projectInfo.createDate);
      this.endDate = this.ConvertToDatetime(this.projectInfo.endDate);

      this.status = this.project.processStatus;
      if (this.projectInfo.projectRequirementsById) {
        this.listSkill = [];
        this.positionList = [];
        this.projectInfo.projectRequirementsById.forEach(el => {

          this.positionList.push({
            id: this.countId,
            value: el
          });
          this.positionList.forEach(val => {
            el.positionId = val.value.positionByPositionId.id;
            el.skillRequirementsById.forEach(element => {
              element.skillId = element.skillBySkillId.id;
            });
          });
          this.listSkill.push(el.skillRequirementsById);
          this.countId = this.countId + 1;
        });
      }
    }
  }

  countDuration(endDate, startDate){
    var oneDay = 24*60*60*1000;
    var dateEnd = new Date(endDate);
    var dateStart = new Date(startDate);
      var diffDays = Math.round(Math.abs((dateEnd.getTime() - dateStart.getTime())/(oneDay)));
      return diffDays;
  }

  loadAllPosition() {
    this.prjService.loadAllPosition().subscribe(
      res => {
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

  onPositionSelected(val: any) {
    this.loadSkillByPositionId(val);
    this.listSkillExp = [];
    this.formPositionModel.skillRequirementsById = [];
  }
  loadSkillByPositionId(id) {
    this.prjService.loadSkillByPositionId(id).subscribe(
      res => {
        this.skillOpt = [];
        this.listSkillExp = [];
        res.forEach(skill => {
          this.skillOpt.push({ value: skill.id.toString(), label: skill.title })
          this.listSkillExp.push(skill);

        });
      },
      err => {
        console.log(err);
      }
    );
  }
  onSkillSelected(val: any) {


    this.formPositionModel.skillRequirementsById = [];
    // console.log(val);

    // if (val.includes(lastItem)) {
    val.forEach(val => {
      this.listSkillExp.forEach(el => {
        if (el.id.toString() === val) {
          // this.formPositionModel.skillRequirementsById.skillBySkillId.push(el);
          // console.log(this.formPositionModel.skillRequirementsById);

          this.formPositionModel.skillRequirementsById.push({
            skillId: el.id,
            skillBySkillId: {
              id: el.id,
              title: el.title
            },
          });
        }
      });
    });
    
  }

  setDisableUntilForStartDate() {
    let now = new Date();
    let optionsStart = JSON.parse(JSON.stringify(this.myDatePickerOptionsStart));
    optionsStart.disableUntil = { year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate() };
    this.myDatePickerOptionsStart = optionsStart;
  }
  onDateChangedCreate(event: IMyDateModel) {
    this.projectInfo.createDate = event && event.jsdate ? event.jsdate.getTime() : null;
    
    let startDate = event.date;
    let optionsEnd = JSON.parse(JSON.stringify(this.myDatePickerOptionsEnd));
    optionsEnd.disableUntil = startDate;
    this.myDatePickerOptionsEnd = optionsEnd;

    // set duration onchange
    this.projectInfo.duration = this.countDuration(this.projectInfo.endDate, this.projectInfo.createDate);

  }
  onDateChangedEnd(event: IMyDateModel) {
    this.projectInfo.endDate = event && event.jsdate ? event.jsdate.getTime() : null;

    // set duration onchange
    this.projectInfo.duration = this.countDuration(this.projectInfo.endDate, this.projectInfo.createDate);
  }
  ConvertToDatetime(dateValue) {
    if (!dateValue) {
      return null;
    }
    var date = new Date(dateValue);
    
    

    var dateParse = {
      date: {
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        day: date.getDate()
      }
    };
    return dateParse;
  }
  editPosition(val: any) {
    this.isPositionUpdate = true;
    this.formPositionModel = Object.assign({}, val.value);
    this.positionList.forEach(el => {
      if (el.id === val.id) {
        this.formPositionModel = el.value;
        this.formPositionModel.positionId = el.value.positionId.toString();
        this.loadSkillByPositionId(this.formPositionModel.positionId);
        this.formPositionModel.skillSelect = [];
        let skills = el.value.skillRequirementsById;
        skills.forEach(el => {
          this.formPositionModel.skillSelect.push(el.skillBySkillId.id.toString());
          // this.formPositionModel.skillRequirementsById.push(el);
        });
      }
    });
    // console.log(this.positionList);
    // $("#positionAdd option[id='" + projectRequirement.positionId + "']").prop('selected', true);

  }
  addPosition() {
    this.isPositionUpdate = false;
    this.formPositionModel = new ProjectRequirement();
  }
  addNewPosition() {
    // console.log(this.formPositionModel);
    // var projectRequirement = this.formPositionModel;
    this.positionList.push({
      id: this.countId,
      value: this.formPositionModel
    })
    console.log(this.positionList);
    this.countId = this.countId + 1;

  }

  updateNewPosition() {
    this.positionList.forEach(el => {
      if (el === this.formPositionModel) {

      }
    });
  }
  deletePostion(val: any) {
    this.positionList = this.positionList.filter(function (el) {
      return el !== val;
    })
  }
  updatePrj() {

    var listPosition = [];
    this.positionList.forEach(el => {
      listPosition.push(el.value);
    });
    this.projectInfo.projectRequirementsById = listPosition;


    this.projectInfo.domain = this.projectDomain.toString();
    this.projectInfo.type = this.projectType.toString();

    this.prjService.updateProject(this.projectInfo).subscribe(
      res => {
        (<any>$("#modal_small")).modal("hide");
        this.reloadMatchingResource.emit();
        this.reloadProject.emit();
      },
      err => {
        console.log(err);
      }
    );
  }

  viewListHumanOnProject(projectId) {
    this.showSuccess();

    this.prjService.updateProjectFinish(projectId).subscribe(
      res => {
        this.router.navigate(['rating'], { queryParams: { "projectId": projectId } });
      },
      err => {
        console.log(err);
      }
    );

  }

  showSuccess() {
    this.toastr.success('You are awesome!', 'Success!');
  }

  onScroll() {
    let scrollToTop = window.setInterval(() => {
      let pos = window.pageYOffset;
      if (pos > 0) {
        window.scrollTo(0, pos - 20); // how far to scroll on each step
      } else {
        window.clearInterval(scrollToTop);
      }
    }, 16);
  }
}

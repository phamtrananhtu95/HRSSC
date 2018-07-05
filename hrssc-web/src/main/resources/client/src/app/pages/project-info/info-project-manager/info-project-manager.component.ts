import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Project, ProjectRequirement } from '../../../models';
import { ProjectService } from '../../../services/project.service';
import { IMyDateModel } from 'angular4-datepicker/src/my-date-picker';

@Component({
  selector: 'app-info-project-manager',
  templateUrl: './info-project-manager.component.html',
  styleUrls: ['./info-project-manager.component.css']
})
export class InfoProjectManagerComponent implements OnInit {
  @Input() project: Project
  @Output() reloadMatchingResource: EventEmitter<any> = new EventEmitter();

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


  public listSkill = [];



  constructor(
    private prjService: ProjectService
  ) { }

  ngOnInit() {
    this.loadAllPosition();
    // this.formPositionModel.positionId = '1';


  }

  ngOnChanges() {
    if (this.project) {
      this.projectInfo = Object.assign({}, this.project);
      this.createDate = this.ConvertToDatetime(this.projectInfo.createDate);
      this.endDate = this.ConvertToDatetime(this.projectInfo.endDate);
      if (this.projectInfo.projectRequirementsById) {
        this.listSkill = [];
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
    // val.forEach(val => {
    //   this.listSkillExp.forEach(el => {
    //     this.formPositionModel.skillRequirementsById.push(el);
    //     // if (el.id === val) {
    //     //   var tmp = {
    //     //     skillId: el.id,
    //     //     title: el.title,
    //     //     positionId: el.positionId

    //     //   }
    //     //   this.formPositionModel.skillRequirementsById.push(tmp);
    //     // }
    //   });
    // });
  }
  onDateChangedCreate(event: IMyDateModel) {
    this.projectInfo.createDate = event && event.jsdate ? event.jsdate.getTime() : null;
  }
  onDateChangedEnd(event: IMyDateModel) {
    this.projectInfo.endDate = event && event.jsdate ? event.jsdate.getTime() : null;
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
        this.formPositionModel.positionId = el.value.positionByPositionId.id.toString();
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
  addNewPosition() {

    console.log(this.formPositionModel);
    var projectRequirement = this.formPositionModel;
    this.positionList.push({
      id: this.countId,
      value: projectRequirement
    })
    console.log(this.positionList);
    this.countId = this.countId + 1;


    // ver 2

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
    let vm = this;
    this.prjService.updateProject(this.projectInfo).subscribe(
      res => {
        (<any>$("#modal_small")).modal("hide");
        this.reloadMatchingResource.emit();
        setTimeout(function () {
          vm.onScroll();
        }, 1500);
      },
      err => {
        console.log(err);
      }
    );
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

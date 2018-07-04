import { Component, OnInit, Input } from '@angular/core';
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

  public projectInfo: Project;
  createDate: any = null;
  endDate: any = null;
  public positionList = [];
  public countId = 0;
  public formPositionModel = new ProjectRequirement();
  public positionOpt;
  public skillOpt;
  public listSkillExp;

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
        this.projectInfo.projectRequirementsById.forEach(el => {
          this.positionList.push({
            id: this.countId,
            value: el
          });
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
    // this.formPositionModel.skillRequirementsById = [];
    console.log(val);
    console.log(this.listSkillExp);
    console.log( this.formPositionModel.skillRequirementsById);
    val.forEach(val => {
      this.listSkillExp.forEach(el => {
          if(el.id === val){
            // this.formPositionModel.skillRequirementsById.skillBySkillId.push(el);
            // console.log(this.formPositionModel.skillRequirementsById);
            this.formPositionModel.skillRequirementsById.skillBySkillId.push(el);
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
    // this.isPositionUpdate = true;

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
          this.formPositionModel.skillRequirementsById.push(el);
        });
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

}

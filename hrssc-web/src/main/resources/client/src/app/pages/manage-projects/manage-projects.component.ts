import { Component, OnInit, ViewChild, ElementRef, Renderer2, Input } from '@angular/core';
import { Project, SkillRequirement, ProjectRequirement } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { ProjectService } from '../../services/project.service';
import { skill } from '../../models/skill.model';

declare var $: any;

@Component({
  selector: 'app-manage-projects',
  templateUrl: './manage-projects.component.html',
  styleUrls: ['./manage-projects.component.css']
})
export class ManageProjectsComponent implements OnInit {

  public parentTitle = "Home";
  public title = " - Manage projects";
  public subTitle = " - Project";
  public projects;
  public positions;
  positionSelected: Number;
  skills: skill[];
  isChecked = false;

  public positionList = new Array<ProjectRequirement>();
  public userId;
  public companyId;
  public isUpdateForm: boolean;
  public isUpdatePositionForm: boolean;
  public formModel = new Project();

  constructor(
    private auth: AuthenticateService,
    private prjService: ProjectService,
  ) { }

  ngOnInit() {
    // this.projects = new projectList().projects;
    if (this.auth.checkLogin()) {
      // let userInfo = this.auth.getUserInfo();
      this.userId = this.auth.getUserId();
      this.companyId = this.auth.getCompanyId();
      this.getProjectsByCompanyId();
      this.loadAllPosition();
    }
  }

  getProjectsByCompanyId() {
    this.prjService.getProjectByManagerId(this.userId).subscribe(
      res => {
        // console.log(res);
        this.projects = res;
        this.projects.forEach(element => {
          element.createDate = this.ConvertToDatetime(element.createDate);
        });
      },
      err => {
        console.log(err);
      }
    );
  }

  loadAllPosition() {
    this.prjService.loadAllPosition().subscribe(
      res => {
        this.positions = res;
        this.positionSelected = 3;
      },
      err => {
        console.log(err);
      }
    );
  }


  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
    return dateParse;
  }

  

  onPositionSelected(val: any) {
    this.skills = [];
    $('.item').empty();
    for (let index = 0; index < val.length; index++) {
      const element = val[index];
      this.skills.push(new skill());
      this.skills[index].id = element.id;
      this.skills[index].title = element.title;
    }

  }

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
    var skillExp = new Array<any>();

    var positionId = $('#positionAdd :selected').attr("id");
    $('input[name^=skilExp]').each(function () {
      skillExp.push({
        "skillId": $(this).attr('id'),
        "experience": $(this).val()
      });
    })
    var quantity = $('#quantityAdd').val();

    var projectRequirement = new ProjectRequirement();
    projectRequirement.positionId = positionId;
    projectRequirement.quantity = quantity;
    projectRequirement.skillRequirementsById = skillExp;
    projectRequirement.payment = 0;

    this.positionList.push(projectRequirement)

    console.log(this.positionList);
  }

  deletePostion(val: any) {
    this.positionList = this.positionList.filter(function (el) {
      return el !== val;
    })
  }
  // add project
  addProject(){
    this.isUpdateForm = false;
    this.isUpdatePositionForm = false;
    this.formModel = new Project();
    this.positionList = new Array<ProjectRequirement>();
  }

  addNewProject() {
    this.formModel.userId = this.userId;
    this.formModel.companyId = this.companyId;
    this.formModel.projectRequirementsById = this.positionList;

    this.prjService.addProject(this.formModel).subscribe(
      res => {
        this.getProjectsByCompanyId();
        (<any>$("#modal_small")).modal("hide");
      },
      err => {
        console.log(err);
      }
    );
    // console.log(this.formModel);
  }

  // update project
  updateProject(project: Project) {
    this.isUpdateForm = true;
    this.isUpdatePositionForm = true;
    this.formModel =  Object.assign({}, project);
    this.positionList = project.projectRequirementsById;
    
    // this.formModel = project;
    console.log(this.formModel);
  }


  updatePrj(){
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

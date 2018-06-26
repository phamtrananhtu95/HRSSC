import { Component, OnInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { Project, projectList } from '../../models';
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

  @ViewChild('item') item;


  public parentTitle = "Home";
  public title = " - Manage projects";
  public subTitle = " - Project";
  public projects;
  public positions;
  positionSelected: Number;
  skills: skill[];
  skillSelected: Number;
  public modifyText;
  public testVal;
  isChecked = false;
  public testValue = "";

  public positionObj = [];

  constructor(
    private auth: AuthenticateService,
    private prjService: ProjectService,
  ) { }

  ngOnInit() {
    // this.projects = new projectList().projects;
    if (this.auth.checkLogin()) {
      let userInfo = this.auth.getUserInfo();
      this.prjService.getProjectByManagerId(userInfo.id).subscribe(
        res => {
          this.projects = res;
          this.projects.forEach(element => {
            element.createDate = this.ConvertToDatetime(element.createDate);
          });
        },
        err => {
          console.log(err);
        }
      );

      this.prjService.loadAllPosition().subscribe(
        res => {
          this.positions = res;
          this.positionSelected = 3;
          console.log(this.positions);
        },
        err => {
          console.log(err);
        }
      );
    }

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

    console.log(this.skills);
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
    var positionId = $('#positionAdd :selected').attr("id");
    var skillExp;
    skillExp = [];

    $('input[name^=skilExp]').each(function () {
      skillExp.push({
        "skillId": $(this).attr('id'),
        "experience": $(this).val()
      });
    })
    var quantity = $('#quantityAdd').val();
    var currentPosition = {
      positionID: positionId,
      skilExp: skillExp,
      quantity: quantity
    }
    this.positionObj.push(currentPosition);
    console.log(this.positionObj)
  }
}

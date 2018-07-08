import { Component, OnInit, Input } from '@angular/core';
import { Project } from '../../../models';

@Component({
  selector: 'app-info-project-view',
  templateUrl: './info-project-view.component.html',
  styleUrls: ['./info-project-view.component.css']
})
export class InfoProjectViewComponent implements OnInit {
  @Input() project: Project
  public projectInfo: Project;
  createDate: any = null;
  endDate: any = null;

  constructor() { }

  ngOnInit() {

  }
  
  ngOnChanges() {
    if(this.project){
      this.projectInfo = Object.assign({}, this.project);
      this.createDate = this.ConvertToDatetime(this.projectInfo.createDate);
      this.endDate = this.ConvertToDatetime(this.projectInfo.endDate);

      console.log(this.projectInfo);

    }
  }
  ConvertToDatetime(dateValue) {
    var date = new Date(parseFloat(dateValue));
    var dateParse = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
    return dateParse;
  }
}

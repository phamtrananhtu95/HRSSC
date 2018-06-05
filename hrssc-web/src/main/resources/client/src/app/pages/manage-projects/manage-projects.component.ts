import { Component, OnInit } from '@angular/core';
import { Project, projectList } from '../../models';

@Component({
  selector: 'app-manage-projects',
  templateUrl: './manage-projects.component.html',
  styleUrls: ['./manage-projects.component.css']
})
export class ManageProjectsComponent implements OnInit {

  projects:Project[];
  constructor() { }

  ngOnInit() {
    this.projects = new projectList().projects;
  }

}

import { Component, OnInit, Input } from '@angular/core';
import { Project } from '../../../models';

@Component({
  selector: 'app-info-project-view',
  templateUrl: './info-project-view.component.html',
  styleUrls: ['./info-project-view.component.css']
})
export class InfoProjectViewComponent implements OnInit {

  @Input() project: Project
  constructor() { }

  ngOnInit() {
  }

}

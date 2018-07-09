import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../../services/project.service';

@Component({
  selector: 'app-resource-joined',
  templateUrl: './resource-joined.component.html',
  styleUrls: ['./resource-joined.component.css']
})
export class ResourceJoinedComponent implements OnInit {
  public projectId: number;
  public listResourceJoined = [];

  constructor(
    private route: ActivatedRoute,
    private prjService: ProjectService
  ) { }

  ngOnInit() {
    this.projectId = this.route.snapshot.queryParams['id'];
    this.getJoinedResource();
  }

  getJoinedResource() {
    this.prjService.getJoinedResourceList(this.projectId).subscribe(
      res => {
          this.listResourceJoined = [];
          this.listResourceJoined = res;
      },
      err => {

      }
    );
  }
}

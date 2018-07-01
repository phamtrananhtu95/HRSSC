import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class ProjectService {

  constructor(
    private restService: RestService
  ) { }

  getProjectByManagerId(id){
    let url = 'manage-project/load-project/' + id;
    return this.restService.get(url);
  }

  loadAllPosition(){
    let url = 'load-form-info/positions';
    return this.restService.get(url);
  }
  loadSkillByPositionId(id){
      let url  = 'load-form-info/get-skill-by-position/' + id;
      return this.restService.get(url);
  }

  addProject(project) {
    let url = 'manage-project/add';
    return this.restService.post(url,project);
  }

  updateProject(project){
    let url = 'manage-project/update';
    return this.restService.post(url,project);
  }

}

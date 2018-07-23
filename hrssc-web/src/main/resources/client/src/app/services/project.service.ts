import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class ProjectService {

  constructor(
    private restService: RestService
  ) { }

  getProjects(id) {
    let url = 'home/project-list/' + id;
    return this.restService.get(url);
  }


  getProjectByManagerId(id) {
    let url = 'manage-project/load-project/' + id;
    return this.restService.get(url);
  }

  getProjectByProjectId(userId, projectId) {
    let url = 'manage-project/details/' + userId + '/' + projectId;
    return this.restService.get(url);
  }
  loadAllPosition() {
    let url = 'load-form-info/positions';
    return this.restService.get(url);
  }
  loadSkillByPositionId(id) {
    let url = 'load-form-info/get-skill-by-position/' + id;
    return this.restService.get(url);
  }

  addProject(project) {
    let url = 'manage-project/add';
    return this.restService.post(url, project);
  }

  updateProject(project) {
    let url = 'manage-project/update';
    return this.restService.post(url, project);
  }

  getResourceMatching(userId, projectId) {
    let url = 'matching/get-matched-resource/' + userId + "/" + projectId;
    return this.restService.get(url);
  }

  loadMatchingProject(userId, resourceId) {
    let url = 'matching/get-matched-project/' + userId + '/' + resourceId;
    return this.restService.get(url);
  }

  loadProjectNotInvite(userId, resourceId) {
    let url = "manage-project/get-invitable-project/" + userId + "/" + resourceId;
    return this.restService.get(url);
  }

  getJoinedResourceList(projectId) {
    let url = "manage-project/get-joined-resource/" + projectId;
    return this.restService.get(url);
  }

  getSimilarProject(projectId) {
    let url = 'similar/get-project-list/' + projectId;
    return this.restService.get(url);
  }

  // Finish project
  updateProjectFinish(projectId) {
    let url = 'manage-project/change-status-finish/' + projectId;
    return this.restService.get(url);
  }

}

import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
// import { ENGINE_METHOD_DIGESTS } from 'constants';

@Injectable()
export class EmployeeService {
  constructor(
    private restService: RestService
  ) { }

  getEmployees() {
    let url = 'humanResource';
    return this.restService.get(url);
  }

  getResource(id) {
    let url = 'home/resource-list/' + id;
    return this.restService.get(url);
  }

  getHumanResourceByManagerId(managerID) {
    let url = 'humanResource/get/' + managerID;
    return this.restService.get(url);
  }


  getAppliableByManagerId(userId, projectId) {
    let url = 'humanResource/get-appliable/' + userId + '/' + projectId;
    return this.restService.get(url);
  }

  // getHumanResourceById(humanResourceId) {
  //   let url = 'humanResource/details/' + humanResourceId;

  getHumanResourceById(userId, humanResourceId) {
    let url = 'humanResource/details/' + userId + '/' + humanResourceId;

    return this.restService.get(url);
  }

  getSkills() {
    let url = 'load-form-info/skills';
    return this.restService.get(url);
  }

  addHumanResource(humanResource) {
    let url = 'humanResource/add';
    return this.restService.post(url, humanResource);
  }

  updateHumanResource(humanResource) {
    let url = 'humanResource/update';
    return this.restService.post(url, humanResource);
  }

  inviteHumanResource(invite) {
    let url = "invitation/invite";
    return this.restService.post(url, invite);
  }

  loadHistoryResource(resourceId) {
    let url = "humanResource/history/" + resourceId;
    return this.restService.get(url);
  }

  loadReviewsResource(resourceId) {
    let url = "feedback/loadAllFeedback/" + resourceId;
    return this.restService.get(url);
  }

  loadSimilarHumanResource(resourceId) {
    let url = "similar/get-resource-list/" + resourceId;
    return this.restService.get(url);
  }

  loadFeedBackResource(humanResourceId) {
    let url = "feedback/loadAll/" + humanResourceId;
    return this.restService.get(url);
  }

  feedbackResource(formFeedback){
    let url = "feedback/add";
    return this.restService.post(url, formFeedback);
  }

  loadHumanOfProject(projectId){
    let url = "feedback/load-all-resource/" + projectId;
    return this.restService.get(url);
  }

}

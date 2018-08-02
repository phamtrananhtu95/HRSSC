import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class ManagementService {
  constructor(
    private restService: RestService
  ) { }

  getManagersByCompanyId(companyId) {
    let url = 'manage-manager/' + companyId;
    return this.restService.get(url);
  }

  addManager(manager) {
    let url = 'manage-manager/add';
    return this.restService.post(url, manager);
  }

  editManager(manager) {
    let url = 'manage-manager/update';
    return this.restService.post(url, manager);
  }

  getManagerById(id) {
    let url = 'manage-manager/details/' + id;
    return this.restService.get(url);
  }

  getListProjectByManagerId(id) {
    let url = 'manage-manager/project-list/' + id;
    return this.restService.get(url);
  }
  getListResourceByManagerId(id) {
    let url = 'manage-manager/resource-list/' + id;
    return this.restService.get(url);
  }
}

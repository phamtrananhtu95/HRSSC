import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class SearchService {

  constructor(
    private restService: RestService
  ) { }

  searchByResource(resource) {
    let url = 'search/by-resource';
    return this.restService.post(url, resource);
  }

  searchByProject(project) {
    let url = 'search/by-project';
    return this.restService.post(url, project);
  }
}

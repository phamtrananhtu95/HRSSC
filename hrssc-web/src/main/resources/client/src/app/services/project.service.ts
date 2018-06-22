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

}

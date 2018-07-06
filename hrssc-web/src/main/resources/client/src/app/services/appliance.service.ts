import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class ApplianceService {

  constructor(
    private restService: RestService
  ) { }

  applianceResource(resource){
      let url = 'appliance/apply';
      return this.restService.post(url, resource);
  }

}

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
  
  getAllAppliance(managerId){
      let url = 'appliance/get-all-appliance/'+ managerId;
      return this.restService.get(url);
  }

  acceptResource(resource){
    let url = 'appliance/accept';
    return this.restService.post(url, resource);
  }
}

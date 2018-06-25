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

}

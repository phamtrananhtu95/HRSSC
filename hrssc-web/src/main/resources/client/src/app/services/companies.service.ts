import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class CompaniesService {

  constructor(
    private restService: RestService
  ) { }

  getCompanies() {
    let url = 'manage-companies/all-requests';
    return this.restService.get(url);
  }

  

}

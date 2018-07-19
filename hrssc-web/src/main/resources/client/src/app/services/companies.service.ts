import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
@Injectable()
export class CompaniesService {

  constructor(
    private restService: RestService
  ) { }
  getCompanyByUserId(id) {
    let url = 'home/company-list/' + id;
    return this.restService.get(url);
  }

  getCompanies() {
    let url = 'manage-companies/all-requests';
    return this.restService.get(url);
  }

  acceptCompany(companyId) {
    let url = 'manage-companies/accept-company/' + companyId;
    return this.restService.post(url, null);
  }

  getCompanyInfoById(companyId){
    let url = 'company/details/' + companyId;
    return this.restService.get(url);
  }

  getListCompany() {
    let url = 'manage-companies/get-company-list';
    return this.restService.get(url);
  }

  updateCompany(company) {
    let url = 'company/update';
    return this.restService.post(url, company);
  }
}

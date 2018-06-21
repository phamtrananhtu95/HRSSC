import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class EmployeeService {
  constructor(
    private restService: RestService
  ) { }

  getEmployees() {
    let url = 'humanResource';
    return this.restService.get(url);
  }

  test() {
    let x = 3;
    let url = 'manage-companies/accept-company/' + x;
    return this.restService.posttest(url);
  }

}

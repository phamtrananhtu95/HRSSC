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

  getHumanResourceByManagerId(managerID){
    let url = 'humanResource/get/' + managerID;
    return this.restService.get(url);
  }

}

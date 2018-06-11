import { Injectable } from '@angular/core';
import { RestService } from './rest.service';



@Injectable()
export class EmployeeService {
  public employee: any;
  constructor(
    private restService: RestService
  ) { }

  getEmployees() {
    let url = 'employees';
    return this.restService.get(url);
  }


  
}

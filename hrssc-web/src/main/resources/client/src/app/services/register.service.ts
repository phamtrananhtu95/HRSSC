import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { Register } from '../models/register.model';

@Injectable()
export class RegisterService {

  constructor(private restService: RestService, private register: Register) { }
  
  registerCompany(company) {
    let url = 'company';
    return this.restService.post(url, company);
  }

}

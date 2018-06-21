import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class ChooseDomainService {

  constructor(
    private restService: RestService
  ) { }

  chooseDomain(domain) {
    let url = 'chooseDomain/create';
    return this.restService.post(url, domain);
  }

}

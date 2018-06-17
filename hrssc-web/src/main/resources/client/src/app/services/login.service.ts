import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { User } from '../models';

@Injectable()
export class LoginService {


  constructor(
    private restService: RestService,
    private user: User
  ) { }

  login(body) {
    let path = 'login';
    return this.restService.authorize(path, body);
  }
}

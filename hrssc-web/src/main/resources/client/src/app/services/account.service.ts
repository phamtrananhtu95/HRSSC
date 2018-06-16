import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { User } from '../models/user.model';
import { AppComponent } from '../app.component';

@Injectable()
export class AccountService {

  constructor(public http: Http) { }

  createAccount(user: User){
    return this.http.post(AppComponent.API_URL+'/register',user)
      .map(resp => resp.json());

  }

}

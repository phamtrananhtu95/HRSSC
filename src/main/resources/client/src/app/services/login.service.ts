import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { User } from '../models';
import { Http, Headers, RequestOptions,Response} from '@angular/http';
import { environment } from '../../environments/environment';

@Injectable()
export class LoginService {


  // constructor(private restService: RestService, private user: User) { }

  constructor(public http: Http) { }  
  login(user: User) {
    // let url = 'login';
    // return this.restService.post(url, user);

    let headers = new Headers();
    headers.append('Accept', 'application/json');

    var base64Credential: string = btoa(user.username+ ':'+ user.password);
    headers.append("Authorization", "Basic "+ base64Credential);

    let options = new RequestOptions();
    options.headers=headers;

    return this.http.get(environment.baseUrl+"login", options)
      .map((response: Response) => {
        let user = response.json().principal;
        console.log(user);
        if(user){
          // localStorage

          localStorage.setItem('currentUser', JSON.stringify(user));
        }
      });
  }
}

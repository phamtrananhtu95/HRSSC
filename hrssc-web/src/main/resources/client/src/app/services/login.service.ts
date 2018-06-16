import { Injectable } from '@angular/core';
import { RestService } from './rest.service';
import { User } from '../models';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { environment } from '../../environments/environment';
import { AppComponent } from '../app.component';


@Injectable()
export class LoginService {


  // constructor(, private user: User) { }
  
  constructor(public http: Http,private restService: RestService) { }  

  
  login(user: User) {

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    var base64Credential: string = btoa( user.username+ ':' + user.password);
    headers.append("Authorization", "Basic " + base64Credential);

    console.log(base64Credential);

    const params = new URLSearchParams();
    params.set('username', user.username);
    params.set('password', user.password);



    let options = new RequestOptions({
      params: params
    });
    console.log(options);

      return this.restService.posttest("login", options);
    // ?"+"username="+user.username+"&password="+user.password
    // console.log(options);

    // return this.http.post(AppComponent.API_URL+"/login", options )
      // .map((response: Response) => {
      //   let user = response.json().principal;
      //   console.log(user);
      //   if(user){
      //     // localStorage
      //     localStorage.setItem('currentUser', JSON.stringify(user));
      //   }
      // });
     

  }

  logOut() {
    // remove user from local storage to log user out
    return this.http.post(AppComponent.API_URL+"/logout",{})
      .map((response: Response) => {
        localStorage.removeItem('currentUser');
      });

  }
}

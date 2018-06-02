import { Component, OnInit } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable} from 'rxjs';
import { environment } from '../environments/environment';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';

// interface UserResponse{
//   login: string,
//   bio: string,
//   company: string
// }

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  public user: any;
  
    constructor(private http:Http){
      
    }

  getUser(){
    this.getName().subscribe(
      res => {
        this.user = res;
      }
    )
  }

  getName() {
    let url = 'users/1';
    return this.get(url);
  }

  get(path): Observable<any> {
    return this.http.get(`${environment.baseUrl}${path}`,
      )
      .map((response: any) => response.json())
  }
}

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { environment } from '../../environments/environment';

@Injectable()
export class RestService {

  constructor(private http: Http) { }

  get(path): Observable<any> {
    return this.http.get(`${environment.baseUrl}${path}`)
      .map((response: any) => response.json());
  }

  post(path, body): Observable<any> {
    return this.http.post(`${environment.baseUrl}${path}`, body)
      .map((response: any) => response.json());
  }
  posttest(path): Observable<any> {
    return this.http.post(`${environment.baseUrl}${path}`, null)
      .map((response: any) => response.json());
  }


  authorize(path, body) {
    let headers = new Headers();
    headers.append('Accept', 'application/json')
    // creating base64 encoded String from user name and password
    var base64Credential: string = btoa(body.username + ':' + body.password);
    headers.append("Authorization", "Basic " + base64Credential);
    // headers.append("cache-control", "no-cache");

    let options = new RequestOptions();

    options.headers = headers;

    return this.http.get(`${environment.baseUrl}${path}`, options)
      .map((response: any) => response.json());
  }
}

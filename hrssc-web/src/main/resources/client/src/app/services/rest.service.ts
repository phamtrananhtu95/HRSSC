import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Response } from '@angular/http';
import { environment } from '../../environments/environment';
import { RequestOptions } from '@angular/http/src/base_request_options';
import { ResponseContentType } from '@angular/http/src/enums';
import { AppComponent } from '../app.component';

@Injectable()
export class RestService {

  constructor(private http: Http) { }

  get(path): Observable<any> {
    return this.http.get(`${environment.baseUrl}${path}`)
      .map((response: any) => response.json())
  }

  post(path, body): Observable<any> {
    return this.http.post(`${environment.baseUrl}${path}`,body)
      .map((response: any) => response.json())
  }

  public posttest(cmd: string, data: string): Observable<any> {
    
        const options = new RequestOptions({
          headers: this.getAuthorizedHeaders(),
          responseType: ResponseContentType.Json,
          withCredentials: false
        });
    
        console.log('Options: ' + JSON.stringify(options));
    
        return this.http.post(AppComponent.API_URL, JSON.stringify({
          cmd: cmd,
          data: data}), options)
          
      }
}

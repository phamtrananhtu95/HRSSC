import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Http, Response } from '@angular/http';
import { environment } from '../../environments/environment';

@Injectable()
export class RestService {

  constructor(private http: Http) { }

  get(path): Observable<any> {
    return this.http.get(`${environment.baseUrl}${path}`,
    )
      .map((response: any) => response.json())
  }

  post(path, body): Observable<any> {
    return this.http.post(`${environment.baseUrl}${path}`,body
    )
      .map((response: any) => response.json())
  }

}

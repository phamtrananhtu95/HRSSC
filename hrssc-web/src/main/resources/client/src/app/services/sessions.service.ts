import { Injectable, Inject } from '@angular/core';
import { SESSION_STORAGE, WebStorageService } from 'angular-webstorage-service';
import {Observable} from 'rxjs';

@Injectable()
export class SessionsService {

  constructor(@Inject(SESSION_STORAGE) private storage: WebStorageService) { }


  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
  }

  getFromLocal(key): any {
    console.log('recieved= key:' + key);
    return this.storage.get(key);
  }
}

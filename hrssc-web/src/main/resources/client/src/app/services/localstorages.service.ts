import { Injectable, Inject } from '@angular/core';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';

@Injectable()
export class LocalstoragesService {

  constructor(@Inject(LOCAL_STORAGE) private storage: WebStorageService) { }

  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    this.storage.set(key, val);
  }

  getFromLocal(key): any {
    console.log('recieved= key:' + key);
    return this.storage.get(key);
  }
}

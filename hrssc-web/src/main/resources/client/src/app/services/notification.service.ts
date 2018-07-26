import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class NotificationService {

  constructor(
    private restService: RestService
  ) { }

  getLogNotify(userId) {
    let url = 'notification-log/get-noti/' + userId;
    return this.restService.get(url);
  }
}

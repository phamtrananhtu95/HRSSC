import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class InvitationService {

  constructor(
    private restService: RestService
  ) { }

  getInvites(managerId) {
    let url = "invitation/get-all-invitation/" + managerId;
    return this.restService.get(url);
  }
}

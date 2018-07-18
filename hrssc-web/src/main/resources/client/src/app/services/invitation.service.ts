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

  // getInvites(useId, managerId){
  //   let url = "manage-project/get-invitable-project/" + managerId;
  //   return this.restService.get(url);


  // }

  acceptInvite(formInvite) {
    let url = "invitation/accept";
    return this.restService.post(url, formInvite);
  }

  loadProjectContract(managerId) {
    let url = "invitation/get-resource-offers/" + managerId;
    return this.restService.get(url);
  }

  // acceptOffer(formContract) {
  //   let url = "contract/accept-offer/";
  //   return this.restService.post(url, formContract);
  // }
}

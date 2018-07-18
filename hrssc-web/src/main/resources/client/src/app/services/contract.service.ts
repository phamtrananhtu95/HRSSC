import { Injectable } from '@angular/core';
import { RestService } from './rest.service';

@Injectable()
export class ContractService {

  constructor(
    private restService: RestService
  ) { }

  getContractInfo(invitationId) {
    let url = 'contract/get-contract/' + invitationId;
    return this.restService.get(url);
  }

  changeOffer(formOffer) {
    let url = 'contract/change-offer/';
    return this.restService.post(url, formOffer);
  }

  getLogChatById(contractId) {
    let url = 'contract/get-chat-logs/' + contractId;
    return this.restService.get(url);


  }
  acceptOffer(formContract) {
    let url = "contract/accept-offer/";
    return this.restService.post(url, formContract);
  }
}

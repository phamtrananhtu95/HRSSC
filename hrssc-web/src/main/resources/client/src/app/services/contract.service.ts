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

  loadContractVersions(contractId) {
    let url = "contract/get-version/" + contractId;
    return this.restService.get(url);
  }

  cancelOffer(contractId) {
    let url = "contract/reject-offer/" + contractId;
    return this.restService.get(url);
  }

  // load resource contract
  loadResourceContract(managerId) {
    let url = "contract/load-all-resource-contract/" + managerId;
    return this.restService.get(url);
  }

  // load project contract
  loadProjectContract(managerId) {
    let url = "contract/load-all-project-contract/" + managerId;
    return this.restService.get(url);
  }

  // Load contract info (just for view)
  loadContractInfo(jobId) {
    let url = 'contract/get-job-contract/' + jobId;
    return this.restService.get(url);
  }

  // Cancel contract
  cancelHumanResource(jobId, userId) {
    let url = "contract/cancel-contract/" + jobId + "/" + userId;
    return this.restService.get(url);
  }
}

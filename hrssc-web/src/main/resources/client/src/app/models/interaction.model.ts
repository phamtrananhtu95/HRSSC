export class Interaction {
    id: number;
    type: string;
    humanResourceId: number;
    projectId: number;
    rankingScore: number;
    contractByContractId: ContractByContractId;
    constructor() {
        this.contractByContractId = new ContractByContractId();
    }


}

export class ContractByContractId {
    id: number;
    startDate: number;
    endDate: number;
    salary: number;
    additionalTerms: string;
    latestEditorId: number;
}
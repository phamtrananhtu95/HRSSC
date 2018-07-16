export class Interaction {
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
    startDate: number;
    endDate: number;
    salary: number;
    additionalTerms: string;
}
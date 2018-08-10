export class Invitation {
    id: number;
    fullname: string;
    email: string;
    salary: number;
    countInvite: number;    
    avatar: string;
    interactionsById: InteractionsById[];
    constructor() {
    }
}

export class InteractionsById {
    id: number;
    type: string;
    projectByProjectId: ProjectByProjectId;
    constructor() {
    }
}

export class ProjectByProjectId {
    id: number;
    title: string;
    createDate: number;
    endDate: number;
    duration: number;
    companyByCompanyId: CompanyByCompanyId;
    constructor() {
    }
}

export class CompanyByCompanyId {
    name: string;
    city: string;
    constructor() {
    }
}
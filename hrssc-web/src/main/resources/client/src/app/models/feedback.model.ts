export class Feedback {
    id: number;
    rating: number;
    comment: string;
    timestamp: number;
    userId: number;
    userByUserId: UserByUserId;

    constructor() {

    }
}

export class UserByUserId {
    id: number;
    username: string;
    fullname: string;
    companyByCompanyId: CompanyByCompanyId;
}

export class CompanyByCompanyId {
    name: string;
}
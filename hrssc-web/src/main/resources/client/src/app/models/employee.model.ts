export class Employee {
    id: number;
    fullname: string;
    status: number;
    email: string;
    tel: string;
    availableDate: number;
    availableDuration: number;
    resourceSkillsById: Skill[];
    companyByCompanyId: CompanyEmp;
    companyId: string;
    salary: number;
    userByUserId: UserByUserId;
    skill: string;
    constructor() {
        this.resourceSkillsById = [];
    }
}

export class EmployeeRequest {
    id: number;
    fullname: string;
    status: number;
    email: string;
    tel: string;
    availableDate: number;
    availableDuration: number;
    resourceSkillsById: Skill[];
    companyId: number;
    userId: number;
    salary: number;

    constructor() {
        this.resourceSkillsById = [];
    }
}

export class Skill {
    skillId: number;
    experience: number;
    skillBySkillId: SkillBySkillId;
    constructor() {
    }
}

export class SkillBySkillId {
    title: string;
    constructor() {
    }
}

export class CompanyEmp {
    city: string;
    id: number;
    name: string;
}

export class UserByUserId {
    fullname: string;
    id: number;
}

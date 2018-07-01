export class Employee {
    id: number;
    fullname: string;
    status: number;
    email: string;
    tel: string;
    availableDate: number;
    availableDuration: number;
    resourceSkillsById: Skill[];
    company: string;
    companyId: string;
    salary: number;

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

    constructor(){
    }
}
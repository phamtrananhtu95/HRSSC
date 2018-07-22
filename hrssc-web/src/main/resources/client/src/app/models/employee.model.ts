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
    avgRating: number;
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

// Working history of resource
export class ResourceHistory {
    id: number;
    humanResourceId: number;
    joinedate: number;
    leaveDate: number;
    projectId: number;
    projectByProjectId: ProjectByProjectId;
    feedbacksById: FeedbacksById[];
    status: number;
    constructor(){
        this.feedbacksById = [];
    }
}

export class ProjectByProjectId {
    id: number;
    title: string;
    type: string;
    domain: string;
    companyId: number;
    companyByCompanyId: CompanyByCompanyId
}

export class CompanyByCompanyId {
    id: number;
    name: string;
}

export class FeedbacksById {
    id: number;
    rating: number;
    comment: string;
    timestamp:number;
    jobKnowledge:number;
    workQuality:number;
    cooperation: number;
    attendance: number;
    workAttitude: number;
    userByUserId: UserByUserId;
    constructor(){
    }
}

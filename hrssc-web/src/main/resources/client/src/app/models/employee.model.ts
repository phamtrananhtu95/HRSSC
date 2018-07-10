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

// Working history of resource
export class ResourceHistory {
    id: number;
    humanResourceId: number;
    joinedate: number;
    leaveDate: number;
    projectId: number;
    projectByProjectId: ProjectByProjectId;
    status: number;
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

//Similar emp

// export class HumanResourceSimilar {
//     id: number;
//     similarScore: number;
//     similarResourceId: number;
//     humanResourceBySimilarResourceId: HumanResourceBySimilarResourceId;
// }

// export class HumanResourceBySimilarResourceId {
//         fullname: string;
//         email: string;
//         tel: string;
//         companyId: number;
//         salary: number;
//         "companyByCompanyId": {
//             "name": "ADS Solution",
//             "city": "Ho Chi Minh"
//         },
//         "resourceSkillsById": [
//             {
//                 "skillId": 2,
//                 "experience": 5,
//                 "skillBySkillId": {
//                     "title": "C# .NET"
//                 }
//             },
//             {
//                 "skillId": 16,
//                 "experience": 2,
//                 "skillBySkillId": {
//                     "title": "Java Spring"
//                 }
//             },
//             {
//                 "skillId": 26,
//                 "experience": 6.5,
//                 "skillBySkillId": {
//                     "title": "IELTS"
//                 }
//             }
//         ]
// }
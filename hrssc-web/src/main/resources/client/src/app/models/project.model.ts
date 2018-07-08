export class Project {
    id: number;
    title: string;
    description: string;
    type: string;
    domain: string;
    duration: number;
    createDate: any;
    endDate: any;
    processStatus: number;
    companyId: number;
    userId: number;
    projectRequirementsById: Array<ProjectRequirement>;

    constructor(){
        
    };
}

export class ProjectRequirement {
    payment: number;
    positionId: string;
    skillSelect;
    skillRequirementsById;
    quantity: number;
    constructor(){
        this.skillSelect = [],
        this.skillRequirementsById = [];
    }
    
}

export class SkillRequirement {
    skillId: number;
    experience: number;
}

export class Appliable {
    projectId: number;
    humanResourceId: number;
}

export class AcceptResource {
    id: number;
    projectId: number;
    humanResourceId: number;
}


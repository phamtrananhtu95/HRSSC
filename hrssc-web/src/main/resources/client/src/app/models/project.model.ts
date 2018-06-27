export class Project {
    id: number;
    title: string;
    description: string;
    type: string;
    domain: string;
    duration: number;
    createDate: number;
    endDate: number;
    processStatus: number;
    companyId: number;
    userId: number;
    projectRequirementsById: Array<ProjectRequirement>;

    constructor(){
        
    };
}

export class ProjectRequirement {
    payment: number;
    positionId: number;
    skillRequirementsById: Array<SkillRequirement>
    quantity: number;
}

export class SkillRequirement {
    skillId: number;
    experience: number;
}


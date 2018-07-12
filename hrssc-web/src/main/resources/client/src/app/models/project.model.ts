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
    positionTitle: string;
    skillRequirementsById;
    quantity: number;
    
    constructor(){
        this.skillRequirementsById = [];
    }
    
}

export class SkillRequirement {
    skillId: number;
    skillTitle: string;
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

export class ProjectSimilar {
    id: number;
    similarScore: number;
    similarProjectId: number;
    listSkillTitle;
    projectBySimilarProjectId;
    constructor(){
        this.listSkillTitle = [];
        this.projectBySimilarProjectId = [];
    }

}
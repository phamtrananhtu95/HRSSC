export class ProjectMatch {
    id: number;
    type: string;
    rankingScore: number;
    projectId: number;
    projectByProjectId: ProjectByProjectId;
    constructor() {
    }

}

export class ProjectByProjectId {
    id: number;
    title: string;
    endDate: number;
    duration: number;
    type: string;
    domain: string;
    skillRequirementsById: SkillRequirementsById[];
    constructor() {
        this.skillRequirementsById = [];
    }
}
export class SkillRequirementsById {
    skillBySkillId: SkillBySkillId;
    experience: number;
    constructor() {
    }
}

export class SkillBySkillId {
    title: string;
    constructor() {
    }
}

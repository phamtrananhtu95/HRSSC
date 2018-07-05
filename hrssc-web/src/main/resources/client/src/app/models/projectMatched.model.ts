import { Company } from './company.model';
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
    projectRequirementsById: ProjectRequirementsById[];
    companyByCompanyId: Company;
    combinedSkill: string;
    constructor() {
        this.projectRequirementsById = [];
    }
}

export class ProjectRequirementsById {
    skillRequirementsById: SkillRequirementsById[]
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
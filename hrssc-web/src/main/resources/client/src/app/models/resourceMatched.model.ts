export class ResourceMatch {
    id: number;
    type: string;
    rankingScore: number;
    humanResourceId: number;
    listSkill: string;
    humanResourceByHumanResourceId: humanResourceById;
    constructor() {
        this.listSkill = "";
    }
}

export class humanResourceById {
    id: number;
    fullname: string;
    email: string;
    tel: string;
    salary: string;
    companyByCompanyId: {
        id: number,
        name: string,
        city: string
    }
    resourceSkillsById: resourceSkillsById[]
    constructor(){
        this.resourceSkillsById = [];
    }

}

export class resourceSkillsById{
    id: number;
    skillId: number;
    experience: number;
    skillBySkillId: {
        title: string
    }
    constructor() {
    }
}

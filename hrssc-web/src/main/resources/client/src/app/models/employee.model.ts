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

    constructor() {
    }
}

export class Skill {
    skillId: number;
    experience: number;
}
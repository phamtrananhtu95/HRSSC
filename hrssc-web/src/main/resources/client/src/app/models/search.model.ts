export class Search{
    userId: number;
    location: string;
    skill: string;
    company: string;
    constructor() {
        this.location = "All";
        this.skill = "All";
        this.company = "";
    }
}
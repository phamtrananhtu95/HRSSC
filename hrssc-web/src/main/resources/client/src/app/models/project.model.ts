export class Project {
    id: number;
    name: string;
    duration: number;
    startDate: number;
    skill: string;
    resourceNeed: number;
    discription: string;
    company: string;
    status: string;
    matched: number;
}

export class projectList {
    projects: Project[];
    constructor() {
        this.projects = [];
        let project1 = new Project();
        project1.id = 1;
        project1.name = "sale web app";
        project1.duration = 4;
        project1.startDate = 814579200;
        project1.skill = "java";
        project1.resourceNeed = 2;
        project1.discription = "hihi";
        project1.status = "On going";
        project1.matched=5;
        project1.company = "fpt software";
        
        let project2 = new Project();
        project2.id = 2;
        project2.name = "grab web app";
        project2.duration = 4;
        project2.startDate = 814579200;
        project2.skill = ".Net";
        project2.resourceNeed = 2;
        project2.discription = "bla bla";
        project2.company = "abc software";
        project2.status = "Pending";
        project2.matched=2;

        let project3 = new Project();
        project3.id = 3;
        project3.name = "heath web app";
        project3.duration = 4;
        project3.startDate = 814579200;
        project3.skill = "SQL";
        project3.resourceNeed = 2;
        project3.discription = "hic hic";
        project3.company = "hihi software";
        project3.status = "On going";
        project3.matched=1;

        let project4 = new Project();
        project4.id = 4;
        project4.name = "ecommerce app";
        project4.duration = 4;
        project4.startDate = 814579200;
        project4.skill = "C#";
        project4.resourceNeed = 4;
        project4.discription = ":D";
        project4.company = "haha software";
        project4.status = "Pending";
        project4.matched=4;

        this.projects.push(project1, project2, project3, project4,project4,project4,project4,project4,project4);
    }
}
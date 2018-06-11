export class User {
    id: number;
    name: string;
    dob: number;
    skill: string;
    experience: number;
    phone: string;
    email: string;
    company: string;
    status: string;
    matched: number;
    constructor() {
    }
}

export class UserList {
    users: User[];

    constructor() {
        this.users = [];
        let user1 = new User();
        user1.id = 1;
        user1.name = "Anh Tu";
        user1.dob = 814579200;
        user1.skill = "java, sql";
        user1.experience = 1;
        user1.phone = "01649407777";
        user1.email = "phamtrananhtu@gmail.com";
        user1.company="abc software";
        user1.status="Available";
        user1.matched=3;
        this.users.push(user1);

        let user2 = new User();
        user2.id = 1;
        user2.name = "Nhat Ha";
        user2.dob = 814579200;
        user2.skill = "java, javascript";
        user2.experience = 2;
        user2.phone = "01649409999";
        user2.email = "nhatha@gmail.com";
        user2.company="fpt software";
        user2.status="Inactive";
        user2.matched=4;
        this.users.push(user2);

        let user3 = new User();
        user3.id = 1;
        user3.name = "Thai Thien";
        user3.dob = 814579200;
        user3.skill = "java, sql, javascript";
        user3.experience = 2;
        user3.phone = "01649408888";
        user3.email = "thaithien@gmail.com";
        user3.company="mdb software";
        user3.status="Available";
        user3.matched=1;
        this.users.push(user3);

        let user4 = new User();
        user4.id = 1;
        user4.name = "Phuc";
        user4.dob = 814579200;
        user4.skill = "java, sql";
        user4.experience = 1;
        user4.phone = "01649406666";
        user4.email = "phuc@gmail.com";
        user4.company="hihi software";
        user4.status="Busy";
        user4.matched=0;
        this.users.push(user4);
        this.users.push(user4);
        this.users.push(user4);
        this.users.push(user4);
        this.users.push(user4);
        this.users.push(user4);
        this.users.push(user4);
        this.users.push(user4);
        this.users.push(user4);
    }
}

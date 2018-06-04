import { Component, OnInit } from '@angular/core';
import { User, UserList } from '../../models/user.model';
import { Project, projectList, companyList, Company } from '../../models';

@Component({
  selector: 'hrssc-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public users: User[];
  public projects:Project[];
  public companies:Company[];
  constructor() { }

  ngOnInit() {
    this.users = new UserList().users;
    this.projects= new projectList().projects;
    this.companies = new companyList().companies;
  }

}

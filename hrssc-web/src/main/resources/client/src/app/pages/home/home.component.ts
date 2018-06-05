import { Component, OnInit } from '@angular/core';
import { User, UserList } from '../../models/user.model';
import { Project, projectList, companyList, Company } from '../../models';
import { Router } from '@angular/router';

@Component({
  selector: 'hrssc-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public users: User[];
  public projects:Project[];
  public companies:Company[];
  constructor(private router:Router) { }

  ngOnInit() {
    this.users = new UserList().users;
    this.projects= new projectList().projects;
    this.companies = new companyList().companies;
  }

  viewResourceDetail(user:User){
    this.router.navigate(['manager/resource/info']);
  }

  viewProjectDetail(project:Project){
    this.router.navigate(['manager/project/info']);
  }

  viewCompanyDetail(company:Company){
    this.router.navigate(['company/info']);
  }

}

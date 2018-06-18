import { Component, OnInit } from '@angular/core';
import { CustomFormsModule, CustomValidators } from 'ng2-validation';
import { NgForm } from '@angular/forms';
import { MenuLeftService } from '../../components/menu-left/menu-left.component.service';
import { HeaderService } from '../../components/header/header.component.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public isValidFormSubmitted = false;

  public emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";

  public registerInfo = {
    email: ""
  }
  constructor(
    public menu: MenuLeftService,
    public header: HeaderService
  ) {}

  ngOnInit() { 
    this.menu.hideMenu(true);
    this.header.hideMenu(true);
  }

  onFormSubmit(form: NgForm) {
    this.isValidFormSubmitted = false;
    if (form.invalid) {
      return;
    }
    this.isValidFormSubmitted = true;
    this.registerInfo = form.value;
    // console.log(this.registerInfo);
  }
}
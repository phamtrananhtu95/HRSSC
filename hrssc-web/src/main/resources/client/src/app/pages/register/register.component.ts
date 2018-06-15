import { Component, OnInit } from '@angular/core';
import { CustomFormsModule, CustomValidators } from 'ng2-validation';
import { NgForm } from '@angular/forms';

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
  constructor() { }

  ngOnInit() { }

  onFormSubmit(form: NgForm) {
    this.isValidFormSubmitted = false;
    if (form.invalid) {
      return;
    }
    this.isValidFormSubmitted = true;
    this.registerInfo = form.value;
    console.log(this.registerInfo);
  }
}
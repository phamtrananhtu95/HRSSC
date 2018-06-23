import { Component, OnInit } from '@angular/core';
import { CustomFormsModule, CustomValidators } from 'ng2-validation';
import { NgForm } from '@angular/forms';
import { MenuLeftService } from '../../components/menu-left/menu-left.component.service';
import { HeaderService } from '../../components/header/header.component.service';
import { Register } from '../../models/register.model';
import { RegisterService } from '../../services/register.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers:[RegisterService]
})
export class RegisterComponent implements OnInit {

  public isValidFormSubmitted = false;

  public emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";

  error = '';

  public registerInfo: Register = new Register();

  constructor(
    public menu: MenuLeftService,
    public header: HeaderService,
    private registerService: RegisterService,
    private router: Router
  ) {}

  ngOnInit() { 
    
    this.menu.hideMenu(true);
    this.header.hideHeader(true);
  }

  onFormSubmit(form: NgForm) {
    this.isValidFormSubmitted = false;
    if (form.invalid) {
      return;
    }
    this.isValidFormSubmitted = true;
    // console.log(this.registerInfo);
    this.registerService.registerCompany(this.registerInfo)
      .subscribe(result => {
        if (result) {
          // register success
          this.router.navigate(['/login']);
        } else {
          // register failed
          this.error = 'Something happend';
        }
      }, error => {
        this.error = error;
      });
  }
}
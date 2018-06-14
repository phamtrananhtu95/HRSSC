import { Component, OnInit } from '@angular/core';
import { CustomFormsModule, CustomValidators } from 'ng2-validation';
import { Validators, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  model: FormGroup;
  constructor() {
    let requiredInput = new FormControl('', Validators.required);
    let number = new FormControl('',  CustomValidators.equalTo(requiredInput));

    this.model = new FormGroup({
      // field: new FormControl('', CustomValidators.range([5, 9]))
      number: number,
      requiredInput: requiredInput
    });
  }

  

  ngOnInit() {
    this.model = new FormGroup({
      'requiredInput': new FormControl(this.model, [
        Validators.required,
      ]),
    });
  }

  onSubmit(form) {
    console.log(form);
  }
}

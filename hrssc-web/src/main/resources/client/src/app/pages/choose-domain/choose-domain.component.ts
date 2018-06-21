import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ChooseDomainService } from '../../services/choose-domain.service';
import { ChooseDomain } from '../../models/chooseDomain.model';

@Component({
  selector: 'app-choose-domain',
  templateUrl: './choose-domain.component.html',
  styleUrls: ['./choose-domain.component.css']
})
export class ChooseDomainComponent implements OnInit {
  error = '';

  public chooseDomain: ChooseDomain = new ChooseDomain();

  constructor(
    private router: Router,
    private chooseDomainService: ChooseDomainService
    
  ) { }

  ngOnInit() {
  }

  onFormSubmit(form: NgForm) {
    // this.isValidFormSubmitted = false;
    // if (form.invalid) {
    //   return;
    // }
    // this.isValidFormSubmitted = true;
    // console.log(this.registerInfo);
    this.chooseDomainService.chooseDomain(this.chooseDomain)
      .subscribe(result => {
        if (result) {
          // register success
          console.log(this.chooseDomain);
          this.router.navigate(['/home']);
        } else {
          // register failed
          this.error = 'Something happend';
        }
      }, error => {
        this.error = error;
      });
  }

}

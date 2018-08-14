import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Company } from '../../../models';
import { CompaniesService } from '../../../services/companies.service';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {
  @Input() company: Company;
  @Output() reloadCompanyInfo: EventEmitter<any> = new EventEmitter();

  public companiesInfo: Company;

  constructor(
    private companyService: CompaniesService
  ) { }

  ngOnInit() {
    (<any>window).sweetAlertMin = true;
        (<any>window).componentModalsJs = true;
  }

  ngOnChanges() {
    if(this.company) {
        this.companiesInfo = Object.assign({}, this.company);
        console.log(this.companiesInfo);
    }
  }

  updateCompany() {


    this.companyService.updateCompany(this.companiesInfo).subscribe(
      res => {
        swal("Update Success", "Your profile has been updated!", "success");
        this.reloadCompanyInfo.emit();
      },
      err => {

      }
    );
  }
}

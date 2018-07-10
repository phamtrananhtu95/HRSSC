import { Component, OnInit, Input } from '@angular/core';
import { Company } from '../../../models';
import { CompaniesService } from '../../../services/companies.service';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {
  @Input() company: Company;

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
      },
      err => {

      }
    );
  }
}

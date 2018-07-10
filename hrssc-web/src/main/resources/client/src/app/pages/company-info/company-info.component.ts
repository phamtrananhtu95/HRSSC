import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from '../../services/authenticate.service';
import { ActivatedRoute } from '@angular/router';
import { CompaniesService } from '../../services/companies.service';
import { Company } from '../../models';

@Component({
  selector: 'app-company-info',
  templateUrl: './company-info.component.html',
  styleUrls: ['./company-info.component.css']
})
export class CompanyInfoComponent implements OnInit {
  public companyId: number;
  public company;

  constructor(
    private auth: AuthenticateService,
    private route: ActivatedRoute,
    private companyService: CompaniesService

  ) { }

  ngOnInit() {
    if (this.auth.checkLogin()) {
      this.companyId = this.route.snapshot.queryParams['id'];
      this.getCompanyInfo();
    }
  }

  getCompanyInfo(){
    this.company = new Company();
    this.companyService.getCompanyInfoById(this.companyId).subscribe(
      res => {
          this.company = res;
      },
      err => {

      }
    );
  }

}

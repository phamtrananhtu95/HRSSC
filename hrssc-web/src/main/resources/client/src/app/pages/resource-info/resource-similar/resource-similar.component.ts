import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import * as jQuery from 'jquery';
import { EmployeeService } from '../../../services/employee.service';
import { Router } from '@angular/router';
declare var $: any;

@Component({
    selector: 'app-resource-similar',
    templateUrl: './resource-similar.component.html'
})
export class ResourceSimilarComponent implements OnInit {
    public similars: any;

    @Input() resourceId;
    @Input() skillList;
    constructor(
        private employeeService: EmployeeService,
        private router: Router
    ) { }

    ngOnInit() {
        this.employeeService.loadSimilarHumanResource(this.resourceId).subscribe(
            res => {
                this.similars = res;
                console.log(this.similars);
                
            },
            err => {
                console.log(err);
            }
        )
    }

    viewHumanDetail(humanResourceId) {
        this.router.navigate(['manager/resource/info'], { queryParams: { "id": humanResourceId } });
    }
    viewCompanyDetail(companyId) {
        this.router.navigate(['company/info'], { queryParams: { "id": companyId } });
    }
}

import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Employee, Skill } from './../../../models/employee.model';

import * as jQuery from 'jquery';
import { EmployeeService } from '../../../services/employee.service';
declare var $: any;

@Component({
    selector: 'app-info-resource-view',
    templateUrl: './info-resource-view.component.html'
})
export class InfoResourceViewComponent implements OnInit {


    @Input() humanResource: Employee;
    @Input() skillList;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private employeeService: EmployeeService,
    ) { }

    ngOnChanges() {
        this.getHumanResourceById();
    }

    ngOnInit() {
    }

    getHumanResourceById() {
        let humanResourceId = this.route.snapshot.queryParams['id'];
        this.employeeService.getHumanResourceById(humanResourceId).subscribe(
            res => {
                this.humanResource = res;
            },
            err => {

            }
        )
    }
}

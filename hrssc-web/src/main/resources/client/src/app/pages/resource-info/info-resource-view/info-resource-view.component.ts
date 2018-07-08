import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Employee, Skill } from './../../../models/employee.model';

import * as jQuery from 'jquery';
import { EmployeeService } from '../../../services/employee.service';
import { AuthenticateService } from '../../../services/authenticate.service';
declare var $: any;

@Component({
    selector: 'app-info-resource-view',
    templateUrl: './info-resource-view.component.html'
})
export class InfoResourceViewComponent implements OnInit {


    @Input() humanResource: Employee;
    @Input() skillList;

    public userId: number;

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private employeeService: EmployeeService,
        private authenticateService: AuthenticateService
    ) { }

    ngOnChanges() {
        this.getHumanResourceById();
    }

    ngOnInit() {
        this.userId = this.authenticateService.getUserId();
    }

    getHumanResourceById() {
        let humanResourceId = this.route.snapshot.queryParams['id'];
        if(!this.userId  || !humanResourceId){
            return;
        }
        this.employeeService.getHumanResourceById(this.userId, humanResourceId).subscribe(
            res => {
                this.humanResource = res;
            },
            err => {

            }
        )
    }
}

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

    public skillList: string;

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
        this.skillList = "";
        this.employeeService.getHumanResourceById(humanResourceId).subscribe(
            res => {
                // this.avaliableDate = this.ConvertToDatetime(this.humanResource.availableDate);

                this.humanResource = res;
                // console.log(this.isOwnManager);
                console.log(this.humanResource.availableDate);

                // console.log("----------" + JSON.stringify(this.humanResource));
                let skills = this.humanResource.resourceSkillsById;
                if (!skills || skills.length < 1) {
                    return;
                }
                for (let i = 0; i < skills.length - 1; i++) {
                    this.skillList = this.skillList + skills[i].skillBySkillId.title + ", ";
                }
                this.skillList = this.skillList + skills[skills.length - 1].skillBySkillId.title;
                // console.log("iiiiiiiii" + this.userByUserId);
            },
            err => {

            }
        )
    }
}

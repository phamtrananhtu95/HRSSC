import { skill } from './../../../models/skill.model';
import { EmployeeService } from './../../../services/employee.service';
import { Employee, Skill } from './../../../models/employee.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { IMyDpOptions, IMyDateModel } from 'angular4-datepicker/src/my-date-picker';
import { FormBuilder, FormGroup } from '@angular/forms';

import * as jQuery from 'jquery';
declare var $: any;

@Component({
    selector: 'app-info-resource-manager-view',
    templateUrl: './info-resource-manager.component.html'
})
export class InfoResourceManagerComponent implements OnInit {

    @Input() humanResource: Employee;
    @Output() reloadManagerList: EventEmitter<any> = new EventEmitter();

    public myDatePickerOptions: IMyDpOptions = {
        // other options...
        dateFormat: 'dd/mm/yyyy',
    };
    createDate: any = null;

    public resourceInfo: Employee;
    public skills = [];
    public skillOpts;
    public options = {
        position: ["middle", "center"],
    };

    constructor(
        private employeeService: EmployeeService,

        private _fb: FormBuilder
    ) { }

    ngOnChanges() {
        if (this.humanResource) {
            this.resourceInfo = Object.assign({}, this.humanResource);
            this.createDate = this.ConvertToDatetime(this.resourceInfo.availableDate);
            if (this.resourceInfo.resourceSkillsById && this.resourceInfo.resourceSkillsById.length != 0) {
                for (let i = 0; i < this.resourceInfo.resourceSkillsById.length; i++) {
                    this.skills = [];
                    let skill = this.resourceInfo.resourceSkillsById[i];
                    this.skills.push({
                        skillId: skill.skillId.toString(),
                        index: i,
                        experience: skill.experience
                    });
                }
            }
        }
    }

    ngOnInit() {
        (<any>window).sweetAlertMin = true;
        (<any>window).componentModalsJs = true;

        this.getSkillOpts();
        this.resourceInfo = new Employee();
    }

    onDateChangedCreate(event: IMyDateModel) {
        this.resourceInfo.availableDate = event && event.jsdate ? event.jsdate.getTime() : null;
    }

    ConvertToDatetime(dateValue) {
        if (!dateValue) {
            return null;
        }
        var date = new Date(dateValue);
        var dateParse = {
            date: {
                year: date.getFullYear(),
                month: date.getMonth() + 1,
                day: date.getDate()
            }
        };
        return dateParse;
    }

    updateResource() {
        let vm = this;
        this.resourceInfo.resourceSkillsById = this.skills;
        this.employeeService.updateHumanResource(this.resourceInfo).subscribe(
            res => {
                this.reloadManagerList.emit();
                setTimeout(function () {
                    vm.onScroll();
                }, 1500);
            }
        );

        // console.log(this.resourceInfo);
    }

    getSkillOpts() {
        this.employeeService.getSkills().subscribe(
            res => {
                this.skillOpts = [];
                res.forEach(skill => {
                    this.skillOpts.push({ value: skill.id.toString(), label: skill.title });
                });
                console.log(this.skillOpts);
            },
            err => {
                console.log(err);
            }
        );
    }

    createNewSkill() {
        let index = 0;
        if (this.skills.length >= 1) {
            index = this.skills.length + 1;
        }
        this.skills.push({
            id: "",
            index: index,
            experience: null
        });
        console.log(this.skills);
    }

    removeSkill(index) {
        this.skills = this.skills.filter(skill => skill.index !== index);
    }

    onScroll() {
        let scrollToTop = window.setInterval(() => {
            let pos = window.pageYOffset;
            if (pos > 0) {
                window.scrollTo(0, pos - 20); // how far to scroll on each step
            } else {
                window.clearInterval(scrollToTop);
            }
        }, 16);
    }

}
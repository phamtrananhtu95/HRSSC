import { skill } from './../../../models/skill.model';
import { EmployeeService } from './../../../services/employee.service';
import { Employee, Skill } from './../../../models/employee.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { IMyDpOptions, IMyDateModel } from 'angular4-datepicker/src/my-date-picker';
import { FormBuilder, FormGroup } from '@angular/forms';
import swal from 'sweetalert';

import * as jQuery from 'jquery';
import { ToastsManager } from 'ng2-toastr';
declare var $: any;

@Component({
    selector: 'app-info-resource-manager-view',
    templateUrl: './info-resource-manager.component.html'
})
export class InfoResourceManagerComponent implements OnInit {

    @Input() humanResource: Employee;
    @Output() reloadManagerList: EventEmitter<any> = new EventEmitter();
    @Output() reloadMatchingProject: EventEmitter<any> = new EventEmitter();

    public myDatePickerOptions: IMyDpOptions = {
        dateFormat: 'dd/mm/yyyy',
    };
    startDate: any = null;
    endDate: any = null;

    public resourceInfo = new Employee();
    public skills = [];
    public skillOpts;
    public options = {
        position: ["middle", "center"],
    };

    public myDatePickerOptionsStart: IMyDpOptions = {
        dateFormat: 'dd/mm/yyyy',
    };
    public myDatePickerOptionsEnd: IMyDpOptions = {
        dateFormat: 'dd/mm/yyyy',
    };

    public isBusy: boolean;

    constructor(
        private employeeService: EmployeeService,
        private _fb: FormBuilder,
        private toastr: ToastsManager,

    ) { }

    ngOnChanges() {
        if (this.humanResource) {
            this.resourceInfo = Object.assign({}, this.humanResource);
            this.startDate = this.ConvertToDatetime(this.resourceInfo.availableDate);
            this.endDate = this.ConvertToDatetime(this.resourceInfo.availableDuration);
            if (this.resourceInfo.resourceSkillsById && this.resourceInfo.resourceSkillsById.length != 0) {
                this.skills = [];
                for (let i = 0; i < this.resourceInfo.resourceSkillsById.length; i++) {
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
        this.setDisableUntilForStartDate();

        if (this.humanResource.status == 3) {
            this.isBusy = true;
        } else {
            this.isBusy = false;
        }
        // console.log("abc: " + this.isBusy);
    }

    setDisableUntilForStartDate() {
        let now = new Date();
        let optionsStart = JSON.parse(JSON.stringify(this.myDatePickerOptionsStart));
        optionsStart.disableUntil = { year: now.getFullYear(), month: now.getMonth() + 1, day: now.getDate() };
        this.myDatePickerOptionsStart = optionsStart;
    }

    onDateChangedCreate(event: IMyDateModel) {
        // this.resourceInfo.availableDate = event && event.jsdate ? event.jsdate.getTime() : null;

        this.resourceInfo.availableDate = event && event.jsdate ? event.jsdate.getTime() : null;
        let startDate = event.date;
        let optionsEnd = JSON.parse(JSON.stringify(this.myDatePickerOptionsEnd));
        optionsEnd.disableUntil = startDate;
        this.myDatePickerOptionsEnd = optionsEnd;
    }

    onDateChangedEnd(event: IMyDateModel) {
        this.resourceInfo.availableDuration = event && event.jsdate ? event.jsdate.getTime() : null;
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
                swal("Update Success", "Your resource has been updated!", "success");
                this.reloadManagerList.emit();
                this.reloadMatchingProject.emit();
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

    // Alert
    showSuccess() {
        this.toastr.success('You are awesome!', 'Success!');
    }

}
import { skill } from './../../../models/skill.model';
import { EmployeeService } from './../../../services/employee.service';
import { Employee, Skill } from './../../../models/employee.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import * as jQuery from 'jquery';
declare var $: any;

@Component({
    selector: 'app-info-resource-manager-view',
    templateUrl: './info-resource-manager.component.html'
})
export class InfoResourceManagerComponent implements OnInit {

    @Input() humanResource: Employee;

    public resourceInfo: Employee;
    public skills = [];
    public skillOpts;
    constructor(
        private employeeService: EmployeeService,

    ) { }

    ngOnChanges() {
        if (this.humanResource) {
            this.resourceInfo = Object.assign({}, this.humanResource);
            if (this.resourceInfo.resourceSkillsById && this.resourceInfo.resourceSkillsById.length != 0) {
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
        this.getSkillOpts();
        this.resourceInfo = new Employee();
    }

    updateResource() {
        this.resourceInfo.resourceSkillsById = this.skills;
        this.employeeService.updateHumanResource(this.resourceInfo).subscribe(
            res => {
            }
        );
        console.log(this.resourceInfo);
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

}
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ManagementService } from '../../services/management.service';
import { User, Skill, Employee, EmployeeRequest } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { Manager } from '../../models/manager.model';
import * as jQuery from 'jquery';
import { skill } from '../../models/skill.model';
import { EmployeeService } from '../../services/employee.service';
import { IMyDpOptions, IMyDateModel } from 'angular4-datepicker/src/my-date-picker';
// import { UiSwitchModule } from 'ngx-ui-switch';

declare var $: any;
@Component({
    selector: 'app-resource-manager-popover',
    templateUrl: './resource-manager-popover.component.html'
})
export class ResourceManagerPopoverComponent implements OnInit {

    @Input() editManagerModel: any;
    @Input() isEditForm: boolean;
    @Output() reloadManagerList: EventEmitter<any> = new EventEmitter();

    public formModel = new EmployeeRequest();
    public skillOpts;
    public skills: Skill[];
    public status = null;

    // wizard
    public isChangeTab = false;
    public isEndChangeTab = false;

    public myDatePickerOptions: IMyDpOptions = {
        dateFormat: 'dd/mm/yyyy',
    };

    constructor(
        private managementService: ManagementService,
        private employeeService: EmployeeService,
        private authenticateService: AuthenticateService
    ) { }

    ngOnChanges() {
        // this.formModel = Object.assign({}, this.editManagerModel);
    }

    ngOnInit() {
        (<any>window).wizardStepsTu = true;
        (<any>window).stepsMinTu = true;

        // From wizard_steps.
        (<any>window).resourceManagerPopoverComponent = this;
        this.getSkillOpts();
        this.skills = [];
        this.skills.push(new Skill());

        this.formModel.companyId = this.authenticateService.getCompanyId();
        this.formModel.userId = this.authenticateService.getUserId();

    }

    addNewResource() {
        this.formModel.resourceSkillsById = this.skills;
        this.employeeService.addHumanResource(this.formModel).subscribe(
            res => {
                this.reloadManagerList.emit();
                (<any>$("#modal_default")).modal("hide");
            },
            err => {
                console.log(err);
            }
        )
        console.log(this.formModel);
        // this.managementService.addManager(this.formModel).subscribe(
        //     res => {
        //         this.reloadManagerList.emit();
        //         (<any>$("#modal_small")).modal("hide");
        //     },
        //     err => {
        //         console.log(err);
        //     }
        // );
    }
    testClick() {
        alert(123);
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
        this.skills.push(new Skill());
        console.log(this.skills);
    }

    addHumanResource() {
        this.managementService.editManager(this.formModel).subscribe(
            res => {
                this.reloadManagerList.emit();
                (<any>$("#modal_small")).modal("hide");
            },
            err => {
                console.log(err);
            }
        );
    }

    onDateChangedCreate(event: IMyDateModel) {
        this.formModel.availableDate = event && event.jsdate ? event.jsdate.getTime() : null;
    }

    onDateChangedEnd(event: IMyDateModel) {
        this.formModel.availableDuration = event && event.jsdate ? event.jsdate.getTime() : null;
    }

    onValueChangeStatus($event) {
        this.formModel.status = $event ? 1 : 2;
        // console.log(this.formModel);
    }

    nextTab() {
        this.isChangeTab = true;
        this.isEndChangeTab = false;
    }

    preTab() {
        this.isChangeTab = false;
        this.isEndChangeTab = true;
    }
}

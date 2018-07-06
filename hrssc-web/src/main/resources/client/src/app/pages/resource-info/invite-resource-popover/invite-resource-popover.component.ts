import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import * as jQuery from 'jquery';
import { Employee, Project } from '../../../models';
import { ProjectService } from '../../../services/project.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ProjectMatch } from '../../../models/projectMatched.model';
import { Interaction } from '../../../models/interaction.model';
import { EmployeeService } from '../../../services/employee.service';
declare var $: any;

@Component({
    selector: 'app-invite-resource-popover',
    templateUrl: './invite-resource-popover.component.html'
})
export class InviteResourcePopover implements OnInit {
    @Input() humanResource: Employee;
    @Output() inviteSuccess = new EventEmitter();
    public userIdForProject: number;
    public projects: ProjectMatch[];
    public formModel = new Interaction();
    constructor(
        private projectService: ProjectService,
        private authenticateService: AuthenticateService,
        private employeeService: EmployeeService
    ) { }

    ngOnChanges() {
        this.formModel.humanResourceId = this.humanResource.id;
    }
    ngOnInit() {
        this.userIdForProject = this.authenticateService.getUserId();
        this.projectService.getProjectByManagerId(this.userIdForProject).subscribe(
            res => {
                this.projects = res;
                console.log(this.projects)
            },
            err => {
                console.log(err);
            }
        )
    }

    inviteHumanResource(projectId) {
        this.formModel.projectId = projectId;
        this.employeeService.inviteHumanResource(this.formModel).subscribe(
            res => {
                this.inviteSuccess.emit();
                (<any>$("#modal_theme_info")).modal("hide");
            },
            err => {
                console.log(err);
            }
        );
    }

}

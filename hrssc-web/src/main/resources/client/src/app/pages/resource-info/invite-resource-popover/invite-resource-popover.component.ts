import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import * as jQuery from 'jquery';
import { Employee, Project } from '../../../models';
import { ProjectService } from '../../../services/project.service';
import { AuthenticateService } from '../../../services/authenticate.service';
import { ProjectMatch } from '../../../models/projectMatched.model';
import { Interaction } from '../../../models/interaction.model';
import { EmployeeService } from '../../../services/employee.service';
import { Router } from '@angular/router';
declare var $: any;

@Component({
    selector: 'app-invite-resource-popover',
    templateUrl: './invite-resource-popover.component.html'
})
export class InviteResourcePopover implements OnInit {
    @Input() humanResource: Employee;
    @Output() inviteSuccess = new EventEmitter();
    public userId: number;
    public projects: ProjectMatch[];
    public formModel = new Interaction();
    constructor(
        private projectService: ProjectService,
        private authenticateService: AuthenticateService,
        private employeeService: EmployeeService,
        private router: Router
    ) {
        this.userId = this.authenticateService.getUserId();
    }

    ngOnChanges() {
        if (!this.humanResource) {
            return;
        }
        this.formModel.humanResourceId = this.humanResource.id;
        this.loadProjectNotInvite();
    }
    ngOnInit() {
    }

    loadProjectNotInvite() {
        if (!this.userId || !this.humanResource || !this.humanResource.id) {
            return;
        }
        this.projectService.loadProjectNotInvite(this.userId, this.humanResource.id).subscribe(
            res => {
                this.projects = res;
                this.inviteSuccess.emit({ length: this.projects.length });
                console.log(this.projects)
            },
            err => {
                console.log(err);
            }
        )
    }
    // inviteHumanResource(projectId) {
    //     this.formModel.projectId = projectId;
    //     this.employeeService.inviteHumanResource(this.formModel).subscribe(
    //         res => {
    //             this.loadProjectNotInvite();
    //             (<any>$("#modal_theme_info")).modal("hide");
    //         },
    //         err => {
    //             console.log(err);
    //         }
    //     );
    // }
    viewContract(humanResourceId, projectId) {
        (<any>$("#modal_theme_info")).modal("hide");
        this.router.navigate(['job/contract'], { queryParams: { "humanResourceId": humanResourceId, "projectId": projectId } });
    }
}

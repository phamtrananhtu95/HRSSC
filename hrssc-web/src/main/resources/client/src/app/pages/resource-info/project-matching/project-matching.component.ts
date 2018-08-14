import { Project } from './../../../models/project.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ProjectMatch } from './../../../models/projectMatched.model';
import * as jQuery from 'jquery';
import { ProjectService } from '../../../services/project.service';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../services/employee.service';
import { EmployeeRequest } from '../../../models';
declare var $: any;

@Component({
    selector: 'app-project-matching',
    templateUrl: './project-matching.component.html',
    styleUrls: ['./project-matching.component.css']
})
export class ProjectMatchingComponent {

    @Input() userId: number;
    @Input() resourceId: number;
    public projectsMatched: ProjectMatch[];
    public formModel = new EmployeeRequest();
    constructor(
        private projectService: ProjectService,
        private router: Router,
        private employeeService: EmployeeService
    ) { }

    ngOnChanges() {
        this.projectsMatched = [];
        this.getProjectMatching();
    }
    getProjectMatching() {
        this.projectService.loadMatchingProject(this.userId, this.resourceId).subscribe(res => {
            this.projectsMatched = res;
            console.log(this.projectsMatched);

            this.combineSkillForProject();
        })
    }

    private combineSkillForProject() {
        if (!this.projectsMatched || this.projectsMatched.length === 0) {
            return;
        }
        this.projectsMatched.forEach(project => {
            let projectByProjectId = project.projectByProjectId;
            let skills = [];
            projectByProjectId.projectRequirementsById.forEach(projectReq => {
                projectReq.skillRequirementsById.forEach(skillReq => {
                    skills.push(skillReq.skillBySkillId.title);
                });
            })
            projectByProjectId.combinedSkill = skills.join(', ');
        })
    }

    viewHumanProjectDetail(projectId) {
        this.router.navigate(['manager/project/info'], { queryParams: { "id": projectId } });
    }

    viewCompanyDetail(companyId) {
        this.router.navigate(['company/info'], {queryParams:{"id": companyId}});
      }

    rematchProject() {
        this.formModel.id = this.resourceId;
        this.employeeService.matchingResource(this.formModel).subscribe(
            res => {
                this.getProjectMatching();
            },
            err => {
                console.log(err);
            }
        );
    }

}
import { Project } from './../../../models/project.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ProjectMatch } from './../../../models/projectMatched.model';
import * as jQuery from 'jquery';
import { ProjectService } from '../../../services/project.service';
declare var $: any;

@Component({
    selector: 'app-project-matching',
    templateUrl: './project-matching.component.html'
})
export class ProjectMatchingComponent {

    @Input() userId: number;
    @Input() resourceId: number;
    public projectsMatched: ProjectMatch[];
    
    constructor(
        private projectService: ProjectService
    ) { }

    ngOnChanges() {
        this.projectsMatched = [];
        this.getProjectMatching();
    }
    getProjectMatching() {
        this.projectService.loadMatchingProject(this.userId, this.resourceId).subscribe(res => {
            this.projectsMatched = res;
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

}
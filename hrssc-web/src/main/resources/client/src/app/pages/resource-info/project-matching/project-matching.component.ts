import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ProjectMatch } from './../../../models/projectMatched.model';
import * as jQuery from 'jquery';
declare var $: any;

@Component({
    selector: 'app-project-matching',
    templateUrl: './project-matching.component.html'
})
export class ProjectMatchingComponent implements OnInit {
    public projectMatched = new ProjectMatch();
    constructor(
    ) { }

    ngOnInit() {
    }

}

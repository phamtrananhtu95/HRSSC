import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import * as jQuery from 'jquery';
import { Feedback } from '../../../models/feedback.model';
import { EmployeeService } from '../../../services/employee.service';
import { Employee } from '../../../models';
declare var $: any;

@Component({
    selector: 'app-rating-resource',
    templateUrl: './rating-resource.component.html'
})
export class RatingResourceComponent implements OnInit {

    // @Input() humanResourcea = new Employee();
    public formFeedbacks: Feedback[];
    // public humanResourceId = this.humanResourcea.id;
    constructor(
        private employeeService: EmployeeService
    ) { }

    ngOnInit() {
       
    }
}

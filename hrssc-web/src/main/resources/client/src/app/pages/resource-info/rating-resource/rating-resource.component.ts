import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import * as jQuery from 'jquery';
import { Feedback } from '../../../models/feedback.model';
declare var $: any;

@Component({
    selector: 'app-rating-resource',
    templateUrl: './rating-resource.component.html'
})
export class RatingResourceComponent implements OnInit {

    public formModel = new Feedback();

    constructor(
    ) { }

    ngOnInit() {
        this.formModel.rating = 4.5;
        this.formModel.comment = "he is very good!!!";
    }

}

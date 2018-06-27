import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ManagementService } from '../../services/management.service';
import { User } from '../../models';
import { AuthenticateService } from '../../services/authenticate.service';
import { Manager } from '../../models/manager.model';
import * as jQuery from 'jquery';
declare var $: any;

@Component({
    selector: 'app-account-manager-popover',
    templateUrl: './account-manager-popover.component.html'
})
export class AccountManagerPopoverComponent implements OnInit {

    @Input() editManagerModel: any;
    @Input() isEditForm: boolean;
    @Output() reloadManagerList: EventEmitter<any> = new EventEmitter();

    public formModel = new Manager();

    constructor(
        private managementService: ManagementService
    ) { }

    ngOnChanges() {
        this.formModel = Object.assign({}, this.editManagerModel);
    }

    ngOnInit() {
    }

    addNewManager() {
        this.managementService.addManager(this.formModel).subscribe(
            res => {
                this.reloadManagerList.emit();
                (<any>$("#modal_small")).modal("hide");
            },
            err => {
                console.log(err);
            }
        );
    }

    editNewManager() {
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

}

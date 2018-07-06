import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../services/employee.service';
import { AuthenticateService } from '../../../services/authenticate.service';

@Component({
  selector: 'app-project-info-popup',
  templateUrl: './project-info-popup.component.html',
  styleUrls: ['./project-info-popup.component.css']
})
export class ProjectInfoPopupComponent implements OnInit {
  public userId: number;
  @Input() AvailableResource;

  constructor(
    private empService: EmployeeService,
    private auth: AuthenticateService
  ) { }

  ngOnInit() {
      this.userId = this.auth.getUserId();
  }
  ngOnchanges(){
    if(this.AvailableResource){
      console.log(this.AvailableResource);
    }
  }

  
}

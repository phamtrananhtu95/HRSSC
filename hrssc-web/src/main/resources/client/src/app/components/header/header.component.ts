import { Component, OnInit, Injectable } from '@angular/core';
import { HeaderService } from './header.component.service';
import { AuthenticateService } from '../../services/authenticate.service';

@Injectable()
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers:[HeaderService]
})
export class HeaderComponent implements OnInit {
  private userName: any;

  constructor(
    public header: HeaderService,
    public authenticateService: AuthenticateService
  ) { }

  ngOnInit() {
    this.userName = this.authenticateService.getUserName();
  }

}

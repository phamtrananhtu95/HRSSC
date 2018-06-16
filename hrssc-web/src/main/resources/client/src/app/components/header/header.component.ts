import { Component, OnInit, Injectable } from '@angular/core';
import { HeaderService } from './header.component.service';

@Injectable()
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers:[HeaderService]
})
export class HeaderComponent implements OnInit {

  constructor(header: HeaderService) { }

  ngOnInit() {
  }

}

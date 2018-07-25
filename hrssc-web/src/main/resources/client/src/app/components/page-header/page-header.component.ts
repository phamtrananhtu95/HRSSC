import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-page-header',
  templateUrl: './page-header.component.html',
  styleUrls: ['./page-header.component.css']
})
export class PageHeaderComponent implements OnInit {
  @Input() parentTitle: string;
  @Input() title: string;
  @Input() subTitle: string;

  // @Input() parentLink: string;
  @Input() titleLink: string;
  @Input() subTitleLink: string;
  constructor(

  ) { }

  ngOnInit() {

  }
}

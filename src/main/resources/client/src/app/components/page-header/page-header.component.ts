import { Component, OnInit, Input } from '@angular/core';
import { TitleService } from '../../services/title.service';

@Component({
  selector: 'app-page-header',
  templateUrl: './page-header.component.html',
  styleUrls: ['./page-header.component.css']
})
export class PageHeaderComponent implements OnInit {
  @Input() parentTitle: string;
  @Input() title: string;
  @Input() subTitle: string;

  // private parentTitle: String;
  // private subTitle: String;
  constructor(
    // private titleService: TitleService
  ) { }

  ngOnInit() {
    // this.parentTitle = this.titleService.parentTitle;
    // this.subTitle = this.titleService.subTitle;
  }
}

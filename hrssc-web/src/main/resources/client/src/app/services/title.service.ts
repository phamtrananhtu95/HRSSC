import { Injectable } from '@angular/core';

@Injectable()
export class TitleService {

  public parentTitle: String;
  public title: String;
  public subTitle: String;

  constructor() {
   }

  setTile(parentTitle: String, title: String, subTitle: String) {
    this.parentTitle = parentTitle;
    this.title = title;
    this.subTitle = subTitle
  }

  // getTitle(){
  //   return this.parentTitle, this.title, this.subTitle;
  // }
}

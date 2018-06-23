import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthenticateService } from '../../services/authenticate.service';

@Injectable()
export class HeaderService {
  // private userName: any;

  private hideMenuSource = new BehaviorSubject(false);
  currentVisible = this.hideMenuSource.asObservable();

  // tạo base event
  private userSource = new BehaviorSubject("");
  // lắng nghe event
  userInfo = this.userSource.asObservable();
  constructor() {
  }

  hideHeader(visible: boolean) {
    this.hideMenuSource.next(visible);
  }

  // bắn event
  setUserNametoHead(userName: string) {
    this.userSource.next(userName);
  }
}
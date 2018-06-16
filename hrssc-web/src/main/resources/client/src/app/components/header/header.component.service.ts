import { Component } from '@angular/core';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class HeaderService {
  private hideMenuSource = new BehaviorSubject(false);
  currentVisible = this.hideMenuSource.asObservable();
  constructor() {
  }

  hideMenu(visible: boolean) {
    this.hideMenuSource.next(visible);
  }

}
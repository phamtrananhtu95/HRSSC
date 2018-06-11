import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefManageAccountManagerComponent } from './chef-manage-account-manager.component';

describe('ChefManageAccountManagerComponent', () => {
  let component: ChefManageAccountManagerComponent;
  let fixture: ComponentFixture<ChefManageAccountManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChefManageAccountManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChefManageAccountManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

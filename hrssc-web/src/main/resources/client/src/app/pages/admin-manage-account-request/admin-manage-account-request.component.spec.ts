import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminManageAccountRequestComponent } from './admin-manage-account-request.component';

describe('AdminManageAccountRequestComponent', () => {
  let component: AdminManageAccountRequestComponent;
  let fixture: ComponentFixture<AdminManageAccountRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminManageAccountRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminManageAccountRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

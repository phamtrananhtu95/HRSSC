import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminManageCompanyComponent } from './admin-manage-company.component';

describe('AdminManageCompanyComponent', () => {
  let component: AdminManageCompanyComponent;
  let fixture: ComponentFixture<AdminManageCompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminManageCompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminManageCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

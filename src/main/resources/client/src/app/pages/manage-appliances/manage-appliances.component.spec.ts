import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAppliancesComponent } from './manage-appliances.component';

describe('ManageAppliancesComponent', () => {
  let component: ManageAppliancesComponent;
  let fixture: ComponentFixture<ManageAppliancesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageAppliancesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageAppliancesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

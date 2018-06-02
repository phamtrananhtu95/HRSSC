import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageInvitationsComponent } from './manage-invitations.component';

describe('ManageInvitationsComponent', () => {
  let component: ManageInvitationsComponent;
  let fixture: ComponentFixture<ManageInvitationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageInvitationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageInvitationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

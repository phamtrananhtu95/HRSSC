import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseDomainComponent } from './choose-domain.component';

describe('ChooseDomainComponent', () => {
  let component: ChooseDomainComponent;
  let fixture: ComponentFixture<ChooseDomainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseDomainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseDomainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

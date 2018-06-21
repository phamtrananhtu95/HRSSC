import { TestBed, inject } from '@angular/core/testing';

import { ChooseDomainService } from './choose-domain.service';

describe('ChooseDomainService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChooseDomainService]
    });
  });

  it('should be created', inject([ChooseDomainService], (service: ChooseDomainService) => {
    expect(service).toBeTruthy();
  }));
});

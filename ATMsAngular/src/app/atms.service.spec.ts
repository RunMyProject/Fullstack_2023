import { TestBed } from '@angular/core/testing';

import { ATMsService } from './atms.service';

describe('ATMsService', () => {
  let service: ATMsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ATMsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

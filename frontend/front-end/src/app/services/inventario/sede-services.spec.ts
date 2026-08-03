import { TestBed } from '@angular/core/testing';

import { SedeServices } from './sede-services';

describe('SedeServices', () => {
  let service: SedeServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SedeServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

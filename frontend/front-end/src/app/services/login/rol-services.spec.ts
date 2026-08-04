import { TestBed } from '@angular/core/testing';

import { RolServices } from './rol-services';

describe('RolServices', () => {
  let service: RolServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RolServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

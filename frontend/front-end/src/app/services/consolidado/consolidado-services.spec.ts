import { TestBed } from '@angular/core/testing';

import { ConsolidadoServices } from './consolidado-services';

describe('ConsolidadoServices', () => {
  let service: ConsolidadoServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsolidadoServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

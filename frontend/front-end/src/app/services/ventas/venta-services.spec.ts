import { TestBed } from '@angular/core/testing';

import { VentaServices } from './venta-services';

describe('VentaServices', () => {
  let service: VentaServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VentaServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { InventarioServices } from './inventario-services';

describe('InventarioServices', () => {
  let service: InventarioServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InventarioServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

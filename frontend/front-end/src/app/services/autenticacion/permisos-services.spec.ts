import { TestBed } from '@angular/core/testing';

import { PermisosServices } from './permisos-services';

describe('PermisosServices', () => {
  let service: PermisosServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PermisosServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

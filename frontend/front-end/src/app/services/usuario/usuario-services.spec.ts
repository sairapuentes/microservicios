import { TestBed } from '@angular/core/testing';

import { UsuarioServices } from './usuario-services';

describe('UsuarioServices', () => {
  let service: UsuarioServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UsuarioServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

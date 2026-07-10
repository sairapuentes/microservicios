import { TestBed } from '@angular/core/testing';

import { CategoriaServices } from './categoria-services';

describe('CategoriaServices', () => {
  let service: CategoriaServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoriaServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

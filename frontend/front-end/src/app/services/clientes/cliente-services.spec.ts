import { TestBed } from '@angular/core/testing';

import { ClienteServices } from './cliente-services';

describe('ClienteServices', () => {
  let service: ClienteServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClienteServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

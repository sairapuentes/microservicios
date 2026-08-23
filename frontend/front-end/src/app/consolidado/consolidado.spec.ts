import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Consolidado } from './consolidado';

describe('Consolidado', () => {
  let component: Consolidado;
  let fixture: ComponentFixture<Consolidado>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Consolidado],
    }).compileComponents();

    fixture = TestBed.createComponent(Consolidado);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

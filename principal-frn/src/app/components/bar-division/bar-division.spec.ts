import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BarDivision } from './bar-division';

describe('BarDivision', () => {
  let component: BarDivision;
  let fixture: ComponentFixture<BarDivision>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BarDivision]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BarDivision);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MiniCard2 } from './mini-card-2';

describe('MiniCard2', () => {
  let component: MiniCard2;
  let fixture: ComponentFixture<MiniCard2>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MiniCard2]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MiniCard2);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

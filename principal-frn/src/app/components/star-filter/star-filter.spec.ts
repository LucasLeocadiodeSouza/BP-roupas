import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StarFilter } from './star-filter';

describe('StarFilter', () => {
  let component: StarFilter;
  let fixture: ComponentFixture<StarFilter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StarFilter]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StarFilter);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

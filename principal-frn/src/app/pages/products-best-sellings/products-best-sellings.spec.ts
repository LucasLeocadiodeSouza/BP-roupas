import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsBestSellings } from './products-best-sellings';

describe('ProductsBestSellings', () => {
  let component: ProductsBestSellings;
  let fixture: ComponentFixture<ProductsBestSellings>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsBestSellings]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsBestSellings);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

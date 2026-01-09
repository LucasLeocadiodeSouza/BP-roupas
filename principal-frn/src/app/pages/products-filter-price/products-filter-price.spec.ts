import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsFilterPrice } from './products-filter-price';

describe('ProductsFilterPrice', () => {
  let component: ProductsFilterPrice;
  let fixture: ComponentFixture<ProductsFilterPrice>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsFilterPrice]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsFilterPrice);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

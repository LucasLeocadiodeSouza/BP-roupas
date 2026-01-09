import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsDiscount } from './products-discount';

describe('ProductsDiscount', () => {
  let component: ProductsDiscount;
  let fixture: ComponentFixture<ProductsDiscount>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsDiscount]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsDiscount);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

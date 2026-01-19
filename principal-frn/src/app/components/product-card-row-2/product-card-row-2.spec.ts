import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCardRow2 } from './product-card-row-2';

describe('ProductCardRow2', () => {
  let component: ProductCardRow2;
  let fixture: ComponentFixture<ProductCardRow2>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductCardRow2]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductCardRow2);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

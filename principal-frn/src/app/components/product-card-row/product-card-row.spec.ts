import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCardRow } from './product-card-row';

describe('ProductCardRow', () => {
  let component: ProductCardRow;
  let fixture: ComponentFixture<ProductCardRow>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductCardRow]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductCardRow);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

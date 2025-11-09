import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductMenu } from './product-menu';

describe('ProductMenu', () => {
  let component: ProductMenu;
  let fixture: ComponentFixture<ProductMenu>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductMenu]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductMenu);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

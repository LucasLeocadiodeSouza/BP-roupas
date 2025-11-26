import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertProduct } from './insert-product';

describe('InsertProduct', () => {
  let component: InsertProduct;
  let fixture: ComponentFixture<InsertProduct>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsertProduct]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsertProduct);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { CartForm } from './cart-form';

describe('CartForm', () => {
  let service: CartForm;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CartForm);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

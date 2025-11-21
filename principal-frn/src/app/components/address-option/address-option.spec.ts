import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressOption } from './address-option';

describe('AddressOption', () => {
  let component: AddressOption;
  let fixture: ComponentFixture<AddressOption>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddressOption]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddressOption);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

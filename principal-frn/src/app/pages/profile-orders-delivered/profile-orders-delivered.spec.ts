import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileOrdersDelivered } from './profile-orders-delivered';

describe('ProfileOrdersDelivered', () => {
  let component: ProfileOrdersDelivered;
  let fixture: ComponentFixture<ProfileOrdersDelivered>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileOrdersDelivered]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileOrdersDelivered);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

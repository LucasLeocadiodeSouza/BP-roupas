import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileOrdersShipped } from './profile-orders-shipped';

describe('ProfileOrdersShipped', () => {
  let component: ProfileOrdersShipped;
  let fixture: ComponentFixture<ProfileOrdersShipped>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileOrdersShipped]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileOrdersShipped);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

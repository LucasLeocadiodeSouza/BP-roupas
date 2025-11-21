import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileOrder } from './profile-order';

describe('ProfileOrder', () => {
  let component: ProfileOrder;
  let fixture: ComponentFixture<ProfileOrder>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileOrder]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileOrder);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

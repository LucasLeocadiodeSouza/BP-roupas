import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileListProd } from './profile-list-prod';

describe('ProfileListProd', () => {
  let component: ProfileListProd;
  let fixture: ComponentFixture<ProfileListProd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileListProd]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileListProd);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

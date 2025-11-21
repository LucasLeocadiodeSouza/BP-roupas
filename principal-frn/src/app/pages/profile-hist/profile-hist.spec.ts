import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileHist } from './profile-hist';

describe('ProfileHist', () => {
  let component: ProfileHist;
  let fixture: ComponentFixture<ProfileHist>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileHist]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileHist);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

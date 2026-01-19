import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BannerFit } from './banner-fit';

describe('BannerFit', () => {
  let component: BannerFit;
  let fixture: ComponentFixture<BannerFit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BannerFit]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BannerFit);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

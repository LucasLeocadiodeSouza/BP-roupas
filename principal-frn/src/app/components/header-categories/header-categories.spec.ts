import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderCategories } from './header-categories';

describe('HeaderCategories', () => {
  let component: HeaderCategories;
  let fixture: ComponentFixture<HeaderCategories>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderCategories]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderCategories);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

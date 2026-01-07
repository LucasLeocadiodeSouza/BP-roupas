import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextSugestion } from './text-sugestion';

describe('TextSugestion', () => {
  let component: TextSugestion;
  let fixture: ComponentFixture<TextSugestion>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TextSugestion]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TextSugestion);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

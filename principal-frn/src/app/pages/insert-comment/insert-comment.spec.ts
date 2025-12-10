import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertComment } from './insert-comment';

describe('InsertComment', () => {
  let component: InsertComment;
  let fixture: ComponentFixture<InsertComment>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsertComment]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsertComment);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountAccess } from './account-access';

describe('AccountAccess', () => {
  let component: AccountAccess;
  let fixture: ComponentFixture<AccountAccess>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountAccess]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountAccess);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

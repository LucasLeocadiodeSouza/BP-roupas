import { TestBed } from '@angular/core/testing';

import { ElementsForm } from './elements-form';

describe('ElementsForm', () => {
  let service: ElementsForm;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElementsForm);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

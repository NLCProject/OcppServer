import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericDetailsReturnButtonComponent } from './generic-details-return-button.component';

describe('GenericDetailsReturnButtonComponent', () => {
  let component: GenericDetailsReturnButtonComponent;
  let fixture: ComponentFixture<GenericDetailsReturnButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenericDetailsReturnButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenericDetailsReturnButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

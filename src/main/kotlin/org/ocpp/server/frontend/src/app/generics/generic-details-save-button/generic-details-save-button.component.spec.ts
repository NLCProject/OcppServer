import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericDetailsSaveButtonComponent } from './generic-details-save-button.component';

describe('GenericDetailsSaveButtonComponent', () => {
  let component: GenericDetailsSaveButtonComponent;
  let fixture: ComponentFixture<GenericDetailsSaveButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenericDetailsSaveButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenericDetailsSaveButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

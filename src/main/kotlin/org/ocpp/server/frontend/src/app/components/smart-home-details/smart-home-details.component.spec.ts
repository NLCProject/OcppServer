import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartHomeDetailsComponent } from './smart-home-details.component';

describe('SmartHomeDetailsComponent', () => {
  let component: SmartHomeDetailsComponent;
  let fixture: ComponentFixture<SmartHomeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SmartHomeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartHomeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

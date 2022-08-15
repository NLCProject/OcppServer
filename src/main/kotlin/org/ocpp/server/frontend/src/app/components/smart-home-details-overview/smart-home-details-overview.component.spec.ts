import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartHomeDetailsOverviewComponent } from './smart-home-details-overview.component';

describe('SmartHomeDetailsOverviewComponent', () => {
  let component: SmartHomeDetailsOverviewComponent;
  let fixture: ComponentFixture<SmartHomeDetailsOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SmartHomeDetailsOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartHomeDetailsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

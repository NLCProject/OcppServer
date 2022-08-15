import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartHomeOverviewComponent } from './smart-home-overview.component';

describe('SmartHomeOverviewComponent', () => {
  let component: SmartHomeOverviewComponent;
  let fixture: ComponentFixture<SmartHomeOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SmartHomeOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartHomeOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

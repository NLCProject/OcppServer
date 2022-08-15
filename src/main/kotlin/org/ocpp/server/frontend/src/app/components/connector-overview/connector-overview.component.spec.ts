import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectorOverviewComponent } from './connector-overview.component';

describe('ConnectorOverviewComponent', () => {
  let component: ConnectorOverviewComponent;
  let fixture: ComponentFixture<ConnectorOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConnectorOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConnectorOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

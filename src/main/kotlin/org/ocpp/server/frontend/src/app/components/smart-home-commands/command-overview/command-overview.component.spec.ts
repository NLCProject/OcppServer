import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandOverviewComponent } from './command-overview.component';

describe('CommandOverviewComponent', () => {
  let component: CommandOverviewComponent;
  let fixture: ComponentFixture<CommandOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommandOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommandOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

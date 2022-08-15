import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportsclubIconComponent } from './sportsclub-icon.component';

describe('SportsclubIconComponent', () => {
  let component: SportsclubIconComponent;
  let fixture: ComponentFixture<SportsclubIconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SportsclubIconComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SportsclubIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

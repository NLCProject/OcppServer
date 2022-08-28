import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmartHomeCommandsComponents } from './smart-home-commands.components';

describe('SmartHomeCommandComponent', () => {
  let component: SmartHomeCommandsComponents;
  let fixture: ComponentFixture<SmartHomeCommandsComponents>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SmartHomeCommandsComponents ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SmartHomeCommandsComponents);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

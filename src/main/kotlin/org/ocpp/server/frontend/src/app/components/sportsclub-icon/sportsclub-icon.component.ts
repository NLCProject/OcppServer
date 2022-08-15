import {Component, Input} from '@angular/core';
import {IconModel} from '../../models/IconModel';

@Component({
  selector: 'app-sportsclub-icon',
  templateUrl: './sportsclub-icon.component.html',
  styleUrls: ['./sportsclub-icon.component.scss']
})
export class SportsclubIconComponent {

  constructor() { }

  @Input()
  models: IconModel[] = [];

  @Input()
  model: IconModel | null = null;
}

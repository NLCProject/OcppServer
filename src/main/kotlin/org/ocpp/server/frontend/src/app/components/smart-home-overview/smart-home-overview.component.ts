import { Component } from '@angular/core';
import {SmartHomeService} from '../../services/smart-home.service';

@Component({
  selector: 'app-smart-home-overview',
  templateUrl: './smart-home-overview.component.html',
  styleUrls: ['./smart-home-overview.component.scss']
})
export class SmartHomeOverviewComponent {

  constructor(
    public service: SmartHomeService
  ) { }
}

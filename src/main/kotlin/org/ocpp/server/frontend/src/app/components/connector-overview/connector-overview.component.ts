import {Component, Input} from '@angular/core';
import {ConnectorService} from '../../services/connector.service';

@Component({
  selector: 'app-connector-overview',
  templateUrl: './connector-overview.component.html',
  styleUrls: ['./connector-overview.component.scss']
})
export class ConnectorOverviewComponent {

  constructor(
    public service: ConnectorService
  ) { }

  @Input()
  public smartHomeId: string = '';

  public getSmartHomeId(): string {
    return this.smartHomeId;
  }
}

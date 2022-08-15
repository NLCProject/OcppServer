import {Injectable} from '@angular/core';
import {RestHelperService} from './rest-helper.service';
import {NamedModel} from '../models/NamedModel';
import {ConnectorModel} from '../models/ConnectorModel';

@Injectable({
  providedIn: 'root'
})
export class ConnectorService extends RestHelperService<ConnectorModel, NamedModel> {
  path = 'connector';
}

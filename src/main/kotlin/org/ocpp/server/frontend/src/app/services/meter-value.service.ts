import {Injectable} from '@angular/core';
import {RestHelperService} from './rest-helper.service';
import {NamedModel} from '../models/NamedModel';
import {MeterValueModel} from '../models/MeterValueModel';

@Injectable({
  providedIn: 'root'
})
export class MeterValueService extends RestHelperService<MeterValueModel, NamedModel> {
  path = 'meter-value';
}

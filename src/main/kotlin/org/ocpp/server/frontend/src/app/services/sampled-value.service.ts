import {Injectable} from '@angular/core';
import {RestHelperService} from './rest-helper.service';
import {NamedModel} from '../models/NamedModel';
import {SampledValueModel} from "../models/SampledValueModel";

@Injectable({
  providedIn: 'root'
})
export class SampledValueService extends RestHelperService<SampledValueModel, NamedModel> {
  path = 'sampled-value';
}

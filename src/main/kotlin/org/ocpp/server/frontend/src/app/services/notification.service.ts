import {Injectable} from '@angular/core';
import {RestHelperService} from './rest-helper.service';
import {NamedModel} from '../models/NamedModel';
import {NotificationModel} from '../models/NotificationModel';

@Injectable({
  providedIn: 'root'
})
export class NotificationService extends RestHelperService<NotificationModel, NamedModel> {
  path = 'notification';
}

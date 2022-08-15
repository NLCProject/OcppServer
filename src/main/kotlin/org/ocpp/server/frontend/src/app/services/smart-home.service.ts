import {Injectable} from '@angular/core';
import {RestHelperService} from './rest-helper.service';
import {NamedModel} from '../models/NamedModel';
import {SmartHomeModel} from '../models/SmartHomeModel';
import {Observable} from 'rxjs';
import {AvailabilityType} from '../models/AvailabilityType';
import {ResetType} from '../models/ResetType';

@Injectable({
  providedIn: 'root'
})
export class SmartHomeService extends RestHelperService<SmartHomeModel, NamedModel> {
  path = 'smart-home';

  public unlockConnector(connectorId: string): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/unlockConnector?connectorId=${connectorId}`;
    return this.http.post<void>(url, null, this.getHeaders());
  }

  public changeAvailability(connectorId: string, type: AvailabilityType): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/changeAvailability?connectorId=${connectorId}&type=${type}`;
    return this.http.post<void>(url, null, this.getHeaders());
  }

  public clearCache(): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/clearCache`;
    return this.http.post<void>(url, null, this.getHeaders());
  }

  public reset(type: ResetType): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/reset?type=${type}`;
    return this.http.post<void>(url, null, this.getHeaders());
  }

  public remoteStartTransaction(connectorId: string): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/remoteStartTransaction?connectorId=${connectorId}`;
    return this.http.post<void>(url, null, this.getHeaders());
  }

  public remoteStopTransaction(connectorId: string): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/remoteStopTransaction?connectorId=${connectorId}`;
    return this.http.post<void>(url, null, this.getHeaders());
  }
}

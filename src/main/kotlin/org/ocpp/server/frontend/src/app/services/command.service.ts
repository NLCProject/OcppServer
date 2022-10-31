import {Injectable} from '@angular/core';
import {RestHeaderService} from './rest-header.service';
import {Observable} from 'rxjs';
import { ModbusCommandDto } from '../models/ModbusCommandDto';
import {ModbusResponse} from '../models/ModbusResponse';

@Injectable({
  providedIn: 'root'
})
export class CommandService extends RestHeaderService {
  path = 'command';

  public getCommandById(smartHomeId: string, commandId: string): Observable<ModbusCommandDto> {
    const url = `${this.getBaseUrl(this.path)}/getCommandById?smartHomeId=${smartHomeId}&commandId=${commandId}`;
    return this.http.get<ModbusCommandDto>(url, this.getHeaders());
  }

  public runCommand(smartHomeId: string, commandId: string): Observable<ModbusResponse> {
    const url = `${this.getBaseUrl(this.path)}/runCommand?smartHomeId=${smartHomeId}&commandId=${commandId}`;
    return this.http.post<ModbusResponse>(url, null, this.getHeaders());
  }
}

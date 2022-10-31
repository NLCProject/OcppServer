import {Injectable} from '@angular/core';
import {RestHeaderService} from "./rest-header.service";
import {Observable} from "rxjs";
import { ModbusCommandDto } from '../models/ModbusCommandDto';

@Injectable({
  providedIn: 'root'
})
export class CommandService extends RestHeaderService {
  path = 'command';

  public getCommandById(smartHomeId: string, commandId: string): Observable<ModbusCommandDto> {
    const url = `${this.getBaseUrl(this.path)}/getCommandById?smartHomeId=${smartHomeId}&commandId=${commandId}`;
    return this.http.get<ModbusCommandDto>(url, this.getHeaders());
  }
}

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export abstract class RestHeaderService {

  protected constructor(
    protected http: HttpClient
  ) { }

  protected getBaseUrl(path: string): string {
    return `${environment.SERVER_URL}:8080/` + path;
  }

  protected getHeaders(): { headers: HttpHeaders } {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
  }
}

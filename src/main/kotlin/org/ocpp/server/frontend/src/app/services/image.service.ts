import {Injectable} from '@angular/core';
import {RestHelperService} from './rest-helper.service';
import {NamedModel} from '../models/NamedModel';
import {ImageModel} from "../models/ImageModel";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ImageService extends RestHelperService<ImageModel, NamedModel> {
  path = 'image';

  public upload(model: ImageModel): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/upload`;
    return this.http.post<void>(url, model, this.getHeaders());
  }

  public delete(id: string): Observable<void> {
    const url = `${this.getBaseUrl(this.path)}/delete?id=${id}`;
    return this.http.post<void>(url, null, this.getHeaders());
  }

  public findImageById(imageId: string): Observable<ImageModel> {
    const url = `${this.getBaseUrl(this.path)}/findById?imageId=${imageId}`;
    return this.http.get<ImageModel>(url, this.getHeaders());
  }
}

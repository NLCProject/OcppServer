import {Injectable} from '@angular/core';
import {RestHelperService} from './rest-helper.service';
import {NamedModel} from '../models/NamedModel';
import {TransactionModel} from "../models/TransactionModel";

@Injectable({
  providedIn: 'root'
})
export class TransactionService extends RestHelperService<TransactionModel, NamedModel> {
  path = 'transaction';
}

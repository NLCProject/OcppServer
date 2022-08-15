import {NotificationViewStatus} from './NotificationViewStatus';
import {ChargePointStatus} from './ChargePointStatus';
import {ChargePointErrorCode} from './ChargePointErrorCode';

/**
 * Auto-generated file. Do NOT change, run test 'TypescriptModelGeneratorTest' instead
 */

export class NotificationModel {
    info: string = '';
    dateTimeCreated: string = '';
    vendorId: string = '';
    vendorErrorCode: string = '';
    viewStatus: NotificationViewStatus | null = null;
    status: ChargePointStatus | null = null;
    errorCode: ChargePointErrorCode | null = null;
    connectorId: string = '';
    id: string = '';
    organisationId: string = '';
    timestampCreated: number = 0;
    timestampLastModified: number = 0;
}
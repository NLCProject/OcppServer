import {Reason} from './Reason';
import {TransactionStatus} from './TransactionStatus';
import {TransactionType} from './TransactionType';

/**
 * Auto-generated file. Do NOT change, run test 'TypescriptModelGeneratorTest' instead
 */

export class TransactionModel {
    dateTimeStarted: string = '';
    dateTimeStopped: string = '';
    reasonToStop: Reason | null = null;
    status: TransactionStatus | null = null;
    type: TransactionType | null = null;
    externalId: number = 0;
    reservationId: number = 0;
    connectorId: string = '';
    id: string = '';
    organisationId: string = '';
    timestampCreated: number = 0;
    timestampLastModified: number = 0;
}
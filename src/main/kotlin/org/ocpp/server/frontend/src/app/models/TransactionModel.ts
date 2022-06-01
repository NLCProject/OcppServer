

/**
 * Auto-generated file. Do NOT change, run test 'TypescriptModelGeneratorTest' instead
 */

export class TransactionModel {
    dateTimeStarted: string = '';
    dateTimeStopped: string = '';
    reasonToStop: eu.chargetime.ocpp.model.core.Reason = null;
    status: org.ocpp.server.enums.TransactionStatus = null;
    type: org.ocpp.server.enums.TransactionType = null;
    externalId: number = 0;
    reservationId: number = 0;
    connectorId: string = '';
    id: string = '';
    organisationId: string = '';
    timestampCreated: number = 0;
    timestampLastModified: number = 0;
}
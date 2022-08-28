import {ImageModel} from './ImageModel';
import {SmartHomeStatus} from './SmartHomeStatus';
import {ModbusCommand} from './ModbusCommand';

/**
 * Auto-generated file. Do NOT change, run test 'TypescriptModelGeneratorTest' instead
 */

export class SmartHomeModel {
    name: string = '';
    identifier: string = '';
    sessionIndex: string = '';
    authorized: boolean = false;
    image: ImageModel | null = null;
    status: SmartHomeStatus | null = null;
    availableCommands: ModbusCommand[] = [];
    id: string = '';
    organisationId: string = '';
    timestampCreated: number = 0;
    timestampLastModified: number = 0;
}
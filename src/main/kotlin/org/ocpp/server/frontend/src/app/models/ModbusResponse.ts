import {ModbusCommand} from './ModbusCommand';
import {Register} from './Register';

/**
 * Auto-generated file. Do NOT change, run test 'TypescriptModelGeneratorTest' instead
 */

export class ModbusResponse {
    idTag: string = '';
    command: ModbusCommand | null = null;
    value: Register | null = null;
    typeUuid: string = '';
}
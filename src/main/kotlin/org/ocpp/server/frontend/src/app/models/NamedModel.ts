import {NamedLineModel} from './NamedLineModel';
import {IconModel} from './IconModel';

/**
 * Auto-generated file. Do NOT change, run test 'TypescriptModelGeneratorTest' instead
 */

export class NamedModel {
    userId: string = '';
    firstLine: NamedLineModel | null = null;
    secondLine: NamedLineModel | null = null;
    thirdLine: NamedLineModel | null = null;
    dateParsed: string = '';
    thumbnail: string = '';
    data: any = null;
    icons: IconModel[] = [];
    id: string = '';
    organisationId: string = '';
    timestampCreated: number = 0;
    timestampLastModified: number = 0;
}
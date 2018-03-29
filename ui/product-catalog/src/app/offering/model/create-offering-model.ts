import { SalesChannel } from '../../sales-channel/detail/sales-channel';
import { Segment } from '../../segment/detail/segment';
import { Document } from '../../document/detail/document';

export class CreateOfferingModel {

    public name: string;
    public validForStartDate: string;
    public validForEndDate: string;
    public warrantyPeriodValue: number;
    public warrantyPeriodUnit: number;
    public returnPeriodValue: number;
    public returnPeriodUnit: number;
    public description: string;
    public externalId: string;
    public productSpecificationId: number;
    public catalogId: number;
    public isSellable: boolean;
    public categoryId: number;
    public salesChannels: SalesChannel[];
    public segments: Segment[];
    public documents: Document[];

}

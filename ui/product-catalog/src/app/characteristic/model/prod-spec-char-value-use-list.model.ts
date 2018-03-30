import {ProdSpecCharValueListModel} from "./prod-spec-char-value-list.model";

export class ProdSpecCharValueUseListModel {
    public prodSpecCharUseId: number;
    public prodSpecCharId: number;
    public prodSpecCharDescription: string;
    public prodSpecCharType : number;
    public prodCharValueList: Array<ProdSpecCharValueListModel>;
}

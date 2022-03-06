import { IGroup } from '@/shared/model/group.model';

export interface IChooseGroupProcess {
  id?: number;
  processInstance?: any | null;
  group?: IGroup | null;
}

export class ChooseGroupProcess implements IChooseGroupProcess {
  constructor(public id?: number, public processInstance?: any | null, public group?: IGroup | null) {}
}

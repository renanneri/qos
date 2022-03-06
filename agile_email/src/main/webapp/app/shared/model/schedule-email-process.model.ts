import { IGroup } from '@/shared/model/group.model';

export interface IScheduleEmailProcess {
  id?: number;
  processInstance?: any | null;
  group?: IGroup | null;
}

export class ScheduleEmailProcess implements IScheduleEmailProcess {
  constructor(public id?: number, public processInstance?: any | null, public group?: IGroup | null) {}
}

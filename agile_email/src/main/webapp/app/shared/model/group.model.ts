export interface IGroup {
  id?: number;
  name?: string | null;
  description?: string | null;
}

export class Group implements IGroup {
  constructor(public id?: number, public name?: string | null, public description?: string | null) {}
}

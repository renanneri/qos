export interface IContact {
  id?: number;
  firstName?: string | null;
  lastName?: string | null;
  gender?: string | null;
  mailAdress?: string | null;
  group?: string | null;
}

export class Contact implements IContact {
  constructor(
    public id?: number,
    public firstName?: string | null,
    public lastName?: string | null,
    public gender?: string | null,
    public mailAdress?: string | null,
    public group?: string | null
  ) {}
}

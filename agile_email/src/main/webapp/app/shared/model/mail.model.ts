export interface IMail {
  id?: number;
  subject?: string | null;
  recipient?: string | null;
  message?: string | null;
  group?: string | null;
}

export class Mail implements IMail {
  constructor(
    public id?: number,
    public subject?: string | null,
    public recipient?: string | null,
    public message?: string | null,
    public group?: string | null
  ) {}
}

import axios from 'axios';

import { IMail } from '@/shared/model/mail.model';

const baseApiUrl = 'api/mail';

export default class MailService {
  public find(id: number): Promise<IMail> {
    return new Promise<IMail>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}

import axios from 'axios';

import { IContact } from '@/shared/model/contact.model';

const baseApiUrl = 'api/contacts';

export default class ContactService {
  public find(id: number): Promise<IContact> {
    return new Promise<IContact>((resolve, reject) => {
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

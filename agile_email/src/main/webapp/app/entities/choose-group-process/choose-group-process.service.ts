import axios from 'axios';

import { IChooseGroupProcess } from '@/shared/model/choose-group-process.model';

const baseApiUrl = 'api/choose-group-processes';

export default class ChooseGroupProcessService {
  public find(id: number): Promise<IChooseGroupProcess> {
    return new Promise<IChooseGroupProcess>((resolve, reject) => {
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

  public create(entity: IChooseGroupProcess): Promise<IChooseGroupProcess> {
    return new Promise<IChooseGroupProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}

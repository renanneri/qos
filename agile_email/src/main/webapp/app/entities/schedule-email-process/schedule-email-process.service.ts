import axios from 'axios';

import { IScheduleEmailProcess } from '@/shared/model/schedule-email-process.model';

const baseApiUrl = 'api/schedule-email-processes';

export default class ScheduleEmailProcessService {
  public find(id: number): Promise<IScheduleEmailProcess> {
    return new Promise<IScheduleEmailProcess>((resolve, reject) => {
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

  public create(entity: IScheduleEmailProcess): Promise<IScheduleEmailProcess> {
    return new Promise<IScheduleEmailProcess>((resolve, reject) => {
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

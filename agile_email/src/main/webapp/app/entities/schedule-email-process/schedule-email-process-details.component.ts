import { Component, Vue, Inject } from 'vue-property-decorator';

import { IScheduleEmailProcess } from '@/shared/model/schedule-email-process.model';
import ScheduleEmailProcessService from './schedule-email-process.service';

@Component
export default class ScheduleEmailProcessDetailsComponent extends Vue {
  @Inject('scheduleEmailProcessService') private scheduleEmailProcessService: () => ScheduleEmailProcessService;
  public scheduleEmailProcess: IScheduleEmailProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveScheduleEmailProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveScheduleEmailProcess(scheduleEmailProcessId) {
    this.isFetching = true;
    this.scheduleEmailProcessService()
      .find(scheduleEmailProcessId)
      .then(
        res => {
          this.scheduleEmailProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}

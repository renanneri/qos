import { Component, Vue, Inject } from 'vue-property-decorator';
import { IScheduleEmailProcess } from '@/shared/model/schedule-email-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ScheduleEmailProcessService from './schedule-email-process.service';

@Component
export default class ScheduleEmailProcessListComponent extends Vue {
  @Inject('scheduleEmailProcessService') private scheduleEmailProcessService: () => ScheduleEmailProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'email-sender';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public scheduleEmailProcessList: IScheduleEmailProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.scheduleEmailProcessService()
      .retrieve()
      .then(
        res => {
          this.scheduleEmailProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}

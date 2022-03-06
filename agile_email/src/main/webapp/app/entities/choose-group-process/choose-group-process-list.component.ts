import { Component, Vue, Inject } from 'vue-property-decorator';
import { IChooseGroupProcess } from '@/shared/model/choose-group-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import ChooseGroupProcessService from './choose-group-process.service';

@Component
export default class ChooseGroupProcessListComponent extends Vue {
  @Inject('chooseGroupProcessService') private chooseGroupProcessService: () => ChooseGroupProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'email-sender';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public chooseGroupProcessList: IChooseGroupProcess[] = [];

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
    this.chooseGroupProcessService()
      .retrieve()
      .then(
        res => {
          this.chooseGroupProcessList = res.data;
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

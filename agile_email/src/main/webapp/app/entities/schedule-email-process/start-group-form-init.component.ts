import { Component, Vue, Inject } from 'vue-property-decorator';

import { IScheduleEmailProcess, ScheduleEmailProcess } from '@/shared/model/schedule-email-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Group } from '@/shared/model/group.model';
import ScheduleEmailProcessService from './schedule-email-process.service';

const validations: any = {
  scheduleEmailProcess: {
    group: {
      GroupName: {},
    },
  },
};

@Component({
  validations,
})
export default class StartGroupFormInitComponent extends Vue {
  @Inject('scheduleEmailProcessService') private scheduleEmailProcessService: () => ScheduleEmailProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'email-sender';
  public scheduleEmailProcess: IScheduleEmailProcess = new ScheduleEmailProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initStartGroupForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.scheduleEmailProcessService()
      .create(this.scheduleEmailProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('agileEmailApp.startGroupForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initStartGroupForm(): void {
    this.scheduleEmailProcess.group = new Group();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.scheduleEmailProcess.processInstance = new ProcessInstance();
      this.scheduleEmailProcess.processInstance.processDefinition = res;
    });
  }
}

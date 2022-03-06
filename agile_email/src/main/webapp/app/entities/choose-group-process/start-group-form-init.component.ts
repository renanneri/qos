import { Component, Vue, Inject } from 'vue-property-decorator';

import { IChooseGroupProcess, ChooseGroupProcess } from '@/shared/model/choose-group-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Group } from '@/shared/model/group.model';
import ChooseGroupProcessService from './choose-group-process.service';

const validations: any = {
  chooseGroupProcess: {
    group: {
      GroupName: {},
    },
  },
};

@Component({
  validations,
})
export default class StartGroupFormInitComponent extends Vue {
  @Inject('chooseGroupProcessService') private chooseGroupProcessService: () => ChooseGroupProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'email-sender';
  public chooseGroupProcess: IChooseGroupProcess = new ChooseGroupProcess();

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

    this.chooseGroupProcessService()
      .create(this.chooseGroupProcess)
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
    this.chooseGroupProcess.group = new Group();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.chooseGroupProcess.processInstance = new ProcessInstance();
      this.chooseGroupProcess.processInstance.processDefinition = res;
    });
  }
}

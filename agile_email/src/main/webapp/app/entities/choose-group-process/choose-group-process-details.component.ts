import { Component, Vue, Inject } from 'vue-property-decorator';

import { IChooseGroupProcess } from '@/shared/model/choose-group-process.model';
import ChooseGroupProcessService from './choose-group-process.service';

@Component
export default class ChooseGroupProcessDetailsComponent extends Vue {
  @Inject('chooseGroupProcessService') private chooseGroupProcessService: () => ChooseGroupProcessService;
  public chooseGroupProcess: IChooseGroupProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveChooseGroupProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveChooseGroupProcess(chooseGroupProcessId) {
    this.isFetching = true;
    this.chooseGroupProcessService()
      .find(chooseGroupProcessId)
      .then(
        res => {
          this.chooseGroupProcess = res;
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

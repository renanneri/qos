import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IMail } from '@/shared/model/mail.model';

import MailService from './mail.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Mail extends Vue {
  @Inject('mailService') private mailService: () => MailService;
  private removeId: number = null;

  public mail: IMail[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllMails();
  }

  public clear(): void {
    this.retrieveAllMails();
  }

  public retrieveAllMails(): void {
    this.isFetching = true;

    this.mailService()
      .retrieve()
      .then(
        res => {
          this.mail = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}

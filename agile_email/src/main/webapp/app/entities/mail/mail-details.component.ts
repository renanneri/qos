import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMail } from '@/shared/model/mail.model';
import MailService from './mail.service';

@Component
export default class MailDetails extends Vue {
  @Inject('mailService') private mailService: () => MailService;
  public mail: IMail = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.mailId) {
        vm.retrieveMail(to.params.mailId);
      }
    });
  }

  public retrieveMail(mailId) {
    this.mailService()
      .find(mailId)
      .then(res => {
        this.mail = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

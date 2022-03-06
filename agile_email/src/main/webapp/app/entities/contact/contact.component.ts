import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IContact } from '@/shared/model/contact.model';

import ContactService from './contact.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Contact extends Vue {
  @Inject('contactService') private contactService: () => ContactService;
  private removeId: number = null;

  public contacts: IContact[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllContacts();
  }

  public clear(): void {
    this.retrieveAllContacts();
  }

  public retrieveAllContacts(): void {
    this.isFetching = true;

    this.contactService()
      .retrieve()
      .then(
        res => {
          this.contacts = res.data;
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

/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MailComponent from '@/entities/mail/mail.vue';
import MailClass from '@/entities/mail/mail.component';
import MailService from '@/entities/mail/mail.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Mail Management Component', () => {
    let wrapper: Wrapper<MailClass>;
    let comp: MailClass;
    let mailServiceStub: SinonStubbedInstance<MailService>;

    beforeEach(() => {
      mailServiceStub = sinon.createStubInstance<MailService>(MailService);
      mailServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<MailClass>(MailComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          mailService: () => mailServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      mailServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllMails();
      await comp.$nextTick();

      // THEN
      expect(mailServiceStub.retrieve.called).toBeTruthy();
      expect(comp.mail[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

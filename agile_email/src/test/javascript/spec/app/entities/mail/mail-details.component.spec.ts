/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import MailDetailComponent from '@/entities/mail/mail-details.vue';
import MailClass from '@/entities/mail/mail-details.component';
import MailService from '@/entities/mail/mail.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Mail Management Detail Component', () => {
    let wrapper: Wrapper<MailClass>;
    let comp: MailClass;
    let mailServiceStub: SinonStubbedInstance<MailService>;

    beforeEach(() => {
      mailServiceStub = sinon.createStubInstance<MailService>(MailService);

      wrapper = shallowMount<MailClass>(MailDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { mailService: () => mailServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMail = { id: 123 };
        mailServiceStub.find.resolves(foundMail);

        // WHEN
        comp.retrieveMail(123);
        await comp.$nextTick();

        // THEN
        expect(comp.mail).toBe(foundMail);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundMail = { id: 123 };
        mailServiceStub.find.resolves(foundMail);

        // WHEN
        comp.beforeRouteEnter({ params: { mailId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.mail).toBe(foundMail);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});

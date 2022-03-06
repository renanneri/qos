/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ContactComponent from '@/entities/contact/contact.vue';
import ContactClass from '@/entities/contact/contact.component';
import ContactService from '@/entities/contact/contact.service';

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
  describe('Contact Management Component', () => {
    let wrapper: Wrapper<ContactClass>;
    let comp: ContactClass;
    let contactServiceStub: SinonStubbedInstance<ContactService>;

    beforeEach(() => {
      contactServiceStub = sinon.createStubInstance<ContactService>(ContactService);
      contactServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ContactClass>(ContactComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          contactService: () => contactServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      contactServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllContacts();
      await comp.$nextTick();

      // THEN
      expect(contactServiceStub.retrieve.called).toBeTruthy();
      expect(comp.contacts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

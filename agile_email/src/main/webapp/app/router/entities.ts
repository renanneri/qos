import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Contact = () => import('@/entities/contact/contact.vue');
// prettier-ignore
const ContactDetails = () => import('@/entities/contact/contact-details.vue');
// prettier-ignore
const Group = () => import('@/entities/group/group.vue');
// prettier-ignore
const GroupDetails = () => import('@/entities/group/group-details.vue');
// prettier-ignore
const Mail = () => import('@/entities/mail/mail.vue');
// prettier-ignore
const MailDetails = () => import('@/entities/mail/mail-details.vue');
// prettier-ignore
const ScheduleEmailProcessDetails = () => import('@/entities/schedule-email-process/schedule-email-process-details.vue');
// prettier-ignore
const ScheduleEmailProcessList = () => import('@/entities/schedule-email-process/schedule-email-process-list.vue');
// prettier-ignore
const StartGroupFormInit = () => import('@/entities/schedule-email-process/start-group-form-init.vue');
// prettier-ignore
const StartGroupFormInit = () => import('@/entities/choose-group-process/start-group-form-init.vue');
// prettier-ignore
const ChooseGroupProcessDetails = () => import('@/entities/choose-group-process/choose-group-process-details.vue');
// prettier-ignore
const ChooseGroupProcessList = () => import('@/entities/choose-group-process/choose-group-process-list.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/contact',
    name: 'Contact',
    component: Contact,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/contact/:contactId/view',
    name: 'ContactView',
    component: ContactDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/group',
    name: 'Group',
    component: Group,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/group/:groupId/view',
    name: 'GroupView',
    component: GroupDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/mail',
    name: 'Mail',
    component: Mail,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/mail/:mailId/view',
    name: 'MailView',
    component: MailDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/email-sender/instance/:processInstanceId/view',
    name: 'ScheduleEmailProcessView',
    component: ScheduleEmailProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/email-sender/instances',
    name: 'ScheduleEmailProcessList',
    component: ScheduleEmailProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/email-sender/init',
    name: 'StartGroupFormInit',
    component: StartGroupFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/email-sender/instance/:processInstanceId/view',
    name: 'ChooseGroupProcessView',
    component: ChooseGroupProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/email-sender/instances',
    name: 'ChooseGroupProcessList',
    component: ChooseGroupProcessList,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];

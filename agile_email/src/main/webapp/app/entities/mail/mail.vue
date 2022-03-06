<template>
  <div>
    <h2 id="page-heading" data-cy="MailHeading">
      <span v-text="$t('agileEmailApp.mail.home.title')" id="mail-heading">Mail</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('agileEmailApp.mail.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && mail && mail.length === 0">
      <span v-text="$t('agileEmailApp.mail.home.notFound')">No mail found</span>
    </div>
    <div class="table-responsive" v-if="mail && mail.length > 0">
      <table class="table table-striped" aria-describedby="mail">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.mail.subject')">Subject</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.mail.recipient')">Recipient</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.mail.message')">Message</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.mail.group')">Group</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="mail in mail" :key="mail.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MailView', params: { mailId: mail.id } }">{{ mail.id }}</router-link>
            </td>
            <td>{{ mail.subject }}</td>
            <td>{{ mail.recipient }}</td>
            <td>{{ mail.message }}</td>
            <td>{{ mail.group }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'MailView', params: { mailId: mail.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="agileEmailApp.mail.delete.question" data-cy="mailDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-mail-heading" v-text="$t('agileEmailApp.mail.delete.question', { id: removeId })">
          Are you sure you want to delete this Mail?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-mail"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeMail()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./mail.component.ts"></script>

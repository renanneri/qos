<template>
  <div>
    <h2 id="page-heading" data-cy="ContactHeading">
      <span v-text="$t('agileEmailApp.contact.home.title')" id="contact-heading">Contacts</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('agileEmailApp.contact.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && contacts && contacts.length === 0">
      <span v-text="$t('agileEmailApp.contact.home.notFound')">No contacts found</span>
    </div>
    <div class="table-responsive" v-if="contacts && contacts.length > 0">
      <table class="table table-striped" aria-describedby="contacts">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.contact.firstName')">First Name</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.contact.lastName')">Last Name</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.contact.gender')">Gender</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.contact.mailAdress')">Mail Adress</span></th>
            <th scope="row"><span v-text="$t('agileEmailApp.contact.group')">Group</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="contact in contacts" :key="contact.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ContactView', params: { contactId: contact.id } }">{{ contact.id }}</router-link>
            </td>
            <td>{{ contact.firstName }}</td>
            <td>{{ contact.lastName }}</td>
            <td>{{ contact.gender }}</td>
            <td>{{ contact.mailAdress }}</td>
            <td>{{ contact.group }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ContactView', params: { contactId: contact.id } }" custom v-slot="{ navigate }">
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
        ><span id="agileEmailApp.contact.delete.question" data-cy="contactDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-contact-heading" v-text="$t('agileEmailApp.contact.delete.question', { id: removeId })">
          Are you sure you want to delete this Contact?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-contact"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeContact()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./contact.component.ts"></script>

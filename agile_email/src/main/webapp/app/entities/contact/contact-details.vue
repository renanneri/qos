<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="contact">
        <h2 class="jh-entity-heading" data-cy="contactDetailsHeading">
          <span v-text="$t('agileEmailApp.contact.detail.title')">Contact</span> {{ contact.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('agileEmailApp.contact.firstName')">First Name</span>
          </dt>
          <dd>
            <span>{{ contact.firstName }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileEmailApp.contact.lastName')">Last Name</span>
          </dt>
          <dd>
            <span>{{ contact.lastName }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileEmailApp.contact.gender')">Gender</span>
          </dt>
          <dd>
            <span>{{ contact.gender }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileEmailApp.contact.mailAdress')">Mail Adress</span>
          </dt>
          <dd>
            <span>{{ contact.mailAdress }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileEmailApp.contact.group')">Group</span>
          </dt>
          <dd>
            <span>{{ contact.group }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./contact-details.component.ts"></script>

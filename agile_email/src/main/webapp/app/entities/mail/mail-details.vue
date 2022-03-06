<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="mail">
        <h2 class="jh-entity-heading" data-cy="mailDetailsHeading">
          <span v-text="$t('agileEmailApp.mail.detail.title')">Mail</span> {{ mail.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('agileEmailApp.mail.subject')">Subject</span>
          </dt>
          <dd>
            <span>{{ mail.subject }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileEmailApp.mail.recipient')">Recipient</span>
          </dt>
          <dd>
            <span>{{ mail.recipient }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileEmailApp.mail.message')">Message</span>
          </dt>
          <dd>
            <span>{{ mail.message }}</span>
          </dd>
          <dt>
            <span v-text="$t('agileEmailApp.mail.group')">Group</span>
          </dt>
          <dd>
            <span>{{ mail.group }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./mail-details.component.ts"></script>

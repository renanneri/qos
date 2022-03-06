<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="scheduleEmailProcessDetailsHeading">
      <span v-text="$t('agileEmailApp.scheduleEmailProcess.home.title')">ScheduleEmailProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('agileEmailApp.scheduleEmailProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/email-sender/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('agileEmailApp.scheduleEmailProcess.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && scheduleEmailProcessList && scheduleEmailProcessList.length === 0">
      <span v-text="$t('agileEmailApp.scheduleEmailProcess.home.notFound')">No scheduleEmailProcess found</span>
    </div>
    <div class="table-responsive" v-if="scheduleEmailProcessList && scheduleEmailProcessList.length > 0">
      <table class="table table-striped" aria-describedby="scheduleEmailProcessList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span>Process Instance</span></th>
            <th scope="row"><span>Group</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Start Date</span></th>
            <th scope="row"><span>End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="scheduleEmailProcess in scheduleEmailProcessList" :key="scheduleEmailProcess.id" data-cy="entityTable">
            <td>{{ scheduleEmailProcess.id }}</td>
            <td>
              <div v-if="scheduleEmailProcess.processInstance">
                <router-link :to="`/process-definition/email-sender/instance/${scheduleEmailProcess.processInstance.id}/view`">
                  {{ scheduleEmailProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="scheduleEmailProcess.group">
                <router-link :to="{ name: 'GroupView', params: { groupId: scheduleEmailProcess.group.id } }">{{
                  scheduleEmailProcess.group.id
                }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="scheduleEmailProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>
              {{
                scheduleEmailProcess.processInstance.startDate
                  ? $d(Date.parse(scheduleEmailProcess.processInstance.startDate), 'short')
                  : ''
              }}
            </td>
            <td>
              {{
                scheduleEmailProcess.processInstance.endDate ? $d(Date.parse(scheduleEmailProcess.processInstance.endDate), 'short') : ''
              }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/email-sender/instance/${scheduleEmailProcess.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./schedule-email-process-list.component.ts"></script>

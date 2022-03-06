<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="agileEmailApp.startGroupForm.home.createOrEditLabel"
          data-cy="StartGroupFormCreateUpdateHeading"
          v-text="$t('agileEmailApp.startGroupForm.home.createOrEditLabel')"
        >
          Create or edit a StartGroupForm
        </h2>
        <div v-if="scheduleEmailProcess.processInstance">
          <akip-show-process-definition :processDefinition="scheduleEmailProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="scheduleEmailProcess.group">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileEmailApp.startGroupForm.GroupName')" for="start-group-form-GroupName"
                    >Group Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="GroupName"
                    id="start-group-form-GroupName"
                    data-cy="GroupName"
                    :class="{
                      valid: !$v.scheduleEmailProcess.group.GroupName.$invalid,
                      invalid: $v.scheduleEmailProcess.group.GroupName.$invalid,
                    }"
                    v-model="$v.scheduleEmailProcess.group.GroupName.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.scheduleEmailProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./start-group-form-init.component.ts"></script>

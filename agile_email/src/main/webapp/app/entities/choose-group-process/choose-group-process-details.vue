<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="chooseGroupProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="chooseGroupProcessDetailsHeading">
          <span v-text="$t('agileEmailApp.chooseGroupProcess.detail.title')">ChooseGroupProcess</span> {{ chooseGroupProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="chooseGroupProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('agileEmailApp.chooseGroupProcess.detail.title')">ChooseGroupProcess</span> #{{ chooseGroupProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="chooseGroupProcess.group">
              <h5 class="card-header">
                <span class="title"> Group #{{ chooseGroupProcess.group.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileEmailApp.chooseGroupProcess.name')">name</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="name"
                    id="group-name"
                    data-cy="name"
                    v-model="chooseGroupProcess.group.name"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('agileEmailApp.chooseGroupProcess.description')">description</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="description"
                    id="group-description"
                    data-cy="description"
                    v-model="chooseGroupProcess.group.description"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./choose-group-process-details.component.ts"></script>

<template>

  <div class="tab-list-div">
    <a-tabs
        v-model:activeKey="activeKey"
        type="card"
        size="large"
    >
      <a-tab-pane key="1" tab="当前课程列表">
        <BrowseTrainingNotificationsComponent/>
      </a-tab-pane>

      <template #rightExtra>
        <a-button type="primary" @click="login">登陆</a-button>
      </template>
    </a-tabs>
  </div>

</template>

<script setup>

import { ref } from 'vue';
import {ElMessage} from "element-plus";
import BrowseTrainingNotificationsComponent from "@/components/home/BrowseTrainingNotificationsComponent.vue";

const activeKey = ref('1');

function login() {
  // TODO login
  ElMessage({
    message: '登陆成功!',
    type: 'success',
    duration: 2 * 1000
  });
}

</script>

<style scoped>

.tab-list-div {
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

</style>

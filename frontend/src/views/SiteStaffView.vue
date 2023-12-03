<template>

  <div class="tab-list-div">
    <a-tabs
        v-model:activeKey="activeKey"
        type="card"
        size="large"
    >
      <a-tab-pane key="1" tab="现场学员管理">
        <SSM/>
      </a-tab-pane>

      <a-tab-pane key="2" tab="调查报告管理">
        <IRM/>
      </a-tab-pane>

      <template #rightExtra>
        <a-button danger @click="logout">退出登陆</a-button>
      </template>
    </a-tabs>
  </div>

</template>

<script setup>

import { ref } from 'vue';
import SSM from '@/components/site_staff/SiteStudentManagementComponent.vue';
import IRM from '@/components/site_staff/InvestigationReportManagementComponent.vue';
import {ElMessage} from "element-plus";
import axios from "axios";

const activeKey = ref('1');

function checkLogin() {
  axios.get('/user/checkLogin').then((res) => {
    if (!res.data.flag) {
      window.location.href = '/';
    }
  }).catch(() => {
    window.location.href = '/';
  });
}

checkLogin();

function logout() {
  axios.get('/user/logout').then((res) => {
    if (res.data.flag) {
      ElMessage({
        message: '退出登陆成功!',
        type: 'success',
        duration: 2 * 1000
      });
      window.location.href = '/';
    }
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

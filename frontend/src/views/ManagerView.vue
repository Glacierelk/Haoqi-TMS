<template>
  <el-watermark :font="font" :content="username" style="height: 100%">

  <div class="tab-list-div">
    <a-tabs
        v-model:activeKey="activeKey"
        type="card"
        size="large"
    >
      <a-tab-pane key="1" tab="培训申请管理">
        <TrainingApplicationComponent/>
      </a-tab-pane>

      <a-tab-pane key="2" tab="培训课程汇总">
        <CourseSummaryComponent/>
      </a-tab-pane>

      <a-tab-pane key="3" tab="执行人工作情况汇总">
        <ExecutorSummaryComponent/>
      </a-tab-pane>

      <a-tab-pane key="4" tab="讲师、学员情况汇总">
        <InstructorSummaryComponent/>
      </a-tab-pane>

      <template #rightExtra>
        <a-button danger @click="logout">退出登陆</a-button>
      </template>
    </a-tabs>
  </div>

  </el-watermark>
</template>

<script setup>

import axios from "axios";
import { ref, reactive } from 'vue';
import {ElMessage} from "element-plus";
import TrainingApplicationComponent from "@/components/manager/TrainingApplicationComponent.vue";
import CourseSummaryComponent from "@/components/manager/CourseSummaryComponent.vue";
import ExecutorSummaryComponent from "@/components/manager/ExecutorSummaryComponent.vue";
import InstructorSummaryComponent from "@/components/manager/InstructorSudentSummaryComponent.vue";

const activeKey = ref('1');
const font = reactive({
  color: 'rgba(0, 0, 0, .15)',
});
const username = ref("");

function checkLogin() {
  axios.get('/user/checkLogin').then((res) => {
    if (!res.data.flag) {
      window.location.href = '/';
    }
  }).catch(() => {
    window.location.href = '/';
  });
}

// TODO need to check
// checkLogin();

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

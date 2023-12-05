<template>

  <div class="tab-list-div">
    <a-tabs
        v-model:activeKey="activeKey"
        type="card"
        size="large"
    >
      <a-tab-pane key="1" tab="审批学员培训报名">
        <ExecutorApprovingRegistrationComponent :executorId="executorId"/>
      </a-tab-pane>

      <a-tab-pane key="2" tab="管理课程">
        <ExecutorInstructorCourseComponent :teacherId="teacherId"/>
      </a-tab-pane>

      <a-tab-pane key="3" tab="管理讲师">
        <ExecutorChangeInstructorComponent @ListenChild="handleListenChild"/>
      </a-tab-pane>

      <a-tab-pane key="4" tab="管理学生">
        <ExecutorChangeStudentComponent/>
      </a-tab-pane>

      <template #rightExtra>
        <a-button danger @click="logout">退出登陆</a-button>
      </template>
    </a-tabs>

  </div>

</template>

<script setup>

import { useRoute } from 'vue-router';
import { ref } from 'vue';
import ExecutorApprovingRegistrationComponent from '@/components/executor/ExecutorApprovingRegistrationComponent.vue';
import ExecutorChangeStudentComponent from "../components/executor/ExecutorChangeStudentComponent.vue";
import ExecutorChangeInstructorComponent from "../components/executor/ExecutorChangeInstructorComponent.vue";
import ExecutorInstructorCourseComponent from "../components/executor/ExecutorInstructorCourseComponent.vue";
import {ElMessage} from "element-plus";
import axios from "axios";

const router = useRoute();
const activeKey = ref('1');
const teacherId = ref('1');
const name = ref("");
const executorId = ref(router.query.executorId);

function checkLogin() {
  axios.get('/user/checkLogin').then((res) => {
    if (!res.data.flag) {
      window.location.href = '/';
    }
  }).catch(() => {
    window.location.href = '/';
  });
}
// 检查登录状态
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

const handleListenChild = (data) => {
  // 处理来自子组件的事件，data 包含子组件传递的参数
  console.log('Received data from child:', data.page,data.teacherId);
  activeKey.value=''+data.page
  teacherId.value =data.teacherId
};

</script>

<style scoped>

.tab-list-div {
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

</style>

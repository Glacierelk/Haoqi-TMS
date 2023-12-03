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

      <a-tab-pane key="2" tab="待完善">
      </a-tab-pane>

      <template #rightExtra>
        <a-button danger @click="logout">退出登陆</a-button>
      </template>
    </a-tabs>
  </div>

  </el-watermark>
</template>

<script setup>

import { ref, reactive } from 'vue';
import {ElMessage} from "element-plus";
import TrainingApplicationComponent from "@/components/manager/TrainingApplicationComponent.vue";
import axios from "axios";

const activeKey = ref('1');
const font = reactive({
  color: 'rgba(0, 0, 0, .15)',
});
const username = ref("");

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

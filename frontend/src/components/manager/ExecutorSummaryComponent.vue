<template>
  <el-row>
    <el-col :span="24">
      <el-statistic title="执行人总数" :value="count">
        <template #prefix>
          <el-icon style="vertical-align: -0.125em">
            <User />
          </el-icon>
        </template>
        <template #suffix>位</template>
      </el-statistic>
    </el-col>
  </el-row>

  <div style="height: 20px" />

  <ExecutorSummaryListComponent />
</template>

<script lang="ts" setup>

import { User } from '@element-plus/icons-vue';
import { ref } from "vue";
import ExecutorSummaryListComponent from "@/components/manager/list/ExecutorSummaryListComponent.vue";
import axios from "axios";
import { ElMessage } from "element-plus";

const count = ref(562);

const getExecutorCount = () => {
  axios.get("/manager/executor/count").then((response) => {
    if (response.data.flag) {
      count.value = response.data.data;
    } else {
      ElMessage({
        message: response.data.message,
        type: "error",
        duration: 2000,
      });
    }
  });
};

getExecutorCount();

</script>

<style scoped>
.el-col {
  text-align: center;
}
</style>

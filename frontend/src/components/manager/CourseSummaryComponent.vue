<template>
  <el-row>
    <el-col :span="12">
      <el-statistic title="开设课程总数" :value="courseCount">
        <template #prefix>
          <el-icon style="vertical-align: -0.125em">
            <Notebook/>
          </el-icon>
        </template>
        <template #suffix>门</template>
      </el-statistic>
    </el-col>
    <el-col :span="12">
      <el-statistic title="已开课课程收入" :value="courseRevenue" precision="2" >
        <template #prefix>
          <el-icon style="vertical-align: -0.125em">
            <Coin />
          </el-icon>
        </template>
        <template #suffix>元</template>
      </el-statistic>
    </el-col>
  </el-row>

  <div style="height: 20px" />

  <CourseSummaryListComponent />
</template>

<script lang="ts" setup>

import { Coin, Notebook } from '@element-plus/icons-vue'
import { ref } from 'vue'
import CourseSummaryListComponent from "@/components/manager/list/CourseSummaryListComponent.vue";
import axios from "axios";

let courseCount = ref(0);
let courseRevenue = ref(0);

const getCourseSummary = () => {
  axios.get('/manager/course/count').then((res) => {
    if (res.data.flag) {
      courseCount.value = res.data.data;
    }
  });
  axios.get('/manager/course/revenue').then((res) => {
    if (res.data.flag) {
      courseRevenue.value = res.data.data;
    }
  });
}

getCourseSummary();

</script>

<style scoped>
.el-col {
  text-align: center;
}
</style>

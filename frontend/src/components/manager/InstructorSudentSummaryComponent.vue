<template>
  <el-row>
    <el-col :span="12">
      <el-statistic title="讲师总数" :value="instructorCount">
        <template #prefix>
          <el-icon style="vertical-align: -0.125em">
            <Avatar />
          </el-icon>
        </template>
        <template #suffix>位</template>
      </el-statistic>
    </el-col>
    <el-col :span="12">
      <el-statistic title="学员总数" :value="studentCount">
        <template #prefix>
          <el-icon style="vertical-align: -0.125em">
            <UserFilled />
          </el-icon>
        </template>
        <template #suffix>人</template>
      </el-statistic>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>

import { Avatar, UserFilled } from '@element-plus/icons-vue';
import { ref } from 'vue';

let instructorCount = ref(10);
let studentCount = ref(10000);

const getCount = () => {
  // TODO: get count from backend
};

getCount();

</script>

<style scoped>
.el-col {
  text-align: center;
}
</style>

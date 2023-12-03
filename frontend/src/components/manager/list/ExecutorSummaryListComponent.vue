<template>
  <el-table :data="tableData" border stripe width="100%">
    <el-table-column align="center" fixed label="序号" >
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="姓名" prop="executorName" />
    <el-table-column align="center" label="管理课程数量" prop="courseCount" sortable />
  </el-table>

  <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20, 50, 100]"
      :total="dataCount"
      layout="total, sizes, prev, pager, next, jumper"
      style="margin-top: 20px; text-align: right; margin-bottom: 20px"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />

  <el-dialog v-model="detailsDialogVisible" title="课程介绍详情" width="50%">
    <div class="autowrap">
      {{ details === null ? "无" : details }}
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="default" @click="detailsDialogVisible = false">关闭</el-button>
      </span>
    </template>
  </el-dialog>

</template>

<script setup>

import {ElMessage} from "element-plus";
import {ref} from 'vue';
import axios from "axios";

let currentPage = ref(1);
let pageSize = ref(10);
let dataCount = ref(0);
let tableData = ref([]);
let detailsDialogVisible = ref(false);
let details = ref("");

const handleSizeChange = (val) => {
  pageSize.value = val;
  search();
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  search();
};

const showDetailsDialog = (description) => {
  detailsDialogVisible.value = true;
  details.value = description;
}

const search = () => {
  axios.get(`/manager/executor/list/${pageSize.value}/${currentPage.value}`).then(
      res => {
        if (res.data.flag) {
          dataCount.value = res.data.data.total;
          console.log(res.data.data.data)
          tableData.value = res.data.data.data;
        } else {
          ElMessage({
            message: "查询失败",
            type: "error",
            duration: 2 * 1000
          })
        }
      }
  ).catch(() => {
    ElMessage({
      message: "查询失败",
      type: "error",
      duration: 2 * 1000
    })
  });
}

search();

</script>

<style scoped>

.autowrap {
  width: 95%;
  word-wrap: break-word; /* 旧的浏览器版本 */
  overflow-wrap: break-word; /* 现代的浏览器版本 */
}

</style>

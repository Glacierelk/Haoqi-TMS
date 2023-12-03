<template>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column align="center" fixed label="序号" width="100">
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="课程名称" prop="name"  width="260"/>
    <el-table-column align="center" label="收入" prop="revenue" sortable width="150">
      <template #default="{ row }">
        <span>{{ row.status === 2 ? '-' : row.revenue.toFixed(2) }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="课程状态" prop="status" width="150">
      <template #default="{ row }">
        <el-tag
            :type="row.status === 0 ? 'info' : row.status === 1 ? 'success' : 'primary'"
            disable-transitions
        >{{ row.status === 0 ? '已结束' : row.status === 1 ? '正在进行' : '未开始' }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column align="center" label="学员平均满意度" prop="averageSatisfactionLevel" width="200" sortable>
      <template #default="{ row }">
        <el-rate v-model="row.averageSatisfactionLevel" disabled text-color="#ff9900" score-template="{value}">
        </el-rate>
      </template>
    </el-table-column>
    <el-table-column label="现场工作人员报告" prop="report" width="1000">
      <template #default="{ row }">
        <span>{{
            row.report == null ? "无" : row.report.length <= 300 ? row.report : row.report.substring(0, 300) + "..."
          }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" fixed="right" label="操作" width="120">
      <template #default="scope">
        <el-button plain size="small" type="primary" @click="showDetailsDialog(scope.row.report)">说明详情
        </el-button>
      </template>
    </el-table-column>
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

const search = async () => {
  await axios.get(`/manager/course/list/${pageSize.value}/${currentPage.value}`).then(
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

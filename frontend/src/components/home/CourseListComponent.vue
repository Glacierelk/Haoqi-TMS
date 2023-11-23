<template>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column align="center" fixed label="序号" width="100">
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="课程名称" prop="name" width="200" />
    <el-table-column label="授课教师" prop="instructorName" width="260" align="center" sortable />
    <el-table-column label="课程时间" width="500" align="center">
      <template #default="{ row }">
        <span>{{ row.startDate }} 至 {{ row.endDate }}</span>
      </template>
    </el-table-column>
    <el-table-column label="课程介绍" prop="description" width="1000">
      <template #default="{ row }">
        <span>{{ row.description.length <= 100 ? row.description : row.description.substring(0, 100) + "..." }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" fixed="right" label="操作" width="160">
      <template #default="scope">
        <el-button plain size="small" type="primary" @click="showDetailsDialog(scope.row.description)">介绍详情</el-button>
        <el-button plain size="small" type="success" @click="showRegisterDialog(scopr.row)">报名</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20, 50, 100]"
      :total="dataCount"
      layout="total, sizes, prev, pager, next, jumper"
      style="margin-top: 20px; text-align: right;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />

  <el-dialog v-model="registerDialogVisible" title="提交报名表" width="50%">
    <span>

    </span>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="submitRegistration">提交</el-button>
        <el-button type="danger" plain @click="ElMessageBox.confirm(
            '确定要关闭吗？',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
        ).then(() => {
          registerDialogVisible = false;
        }).catch(() => {
        })">关闭</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="detailsDialogVisible" title="课程介绍详情" width="50%">
    <div class="autowrap">
      {{ details }}
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="default" @click="detailsDialogVisible = false">关闭</el-button>
      </span>
    </template>
  </el-dialog>

</template>

<script setup>

import {ElMessageBox, ElMessage} from "element-plus";
import {defineProps, ref, watch} from 'vue';
import axios from "axios";

const registerDialogVisible = ref(false);
let currentPage = ref(1);
let pageSize = ref(10);
let dataCount = ref(0);
let tableData = ref([]);
let detailsDialogVisible = ref(false);
let details = ref("");
let courseId = ref("");

// 用于接收父组件传递的数据
const props = defineProps({
  queryForm: {
    type: Object,
    required: true
  },
});

watch(() => props.queryForm, () => {
  pageSize.value = 10;
  currentPage.value = 1;
  search();
});

const submitRegistration = (row) => {
  // TODO 提交报名表
}

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

const showRegisterDialog = (row) => {
  courseId.value = row.courseId;
  registerDialogVisible.value = true;
}

const search = () => {
  // TODO 查询数据
  axios.get(`#`).then(
      res => {
        if (res.data.flag) {
          ElMessage({
            message: "查询成功",
            type: "success",
            duration: 2 * 1000
          });
          tableData.value = res.data.data.data;
          dataCount.value = res.data.data.total;
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
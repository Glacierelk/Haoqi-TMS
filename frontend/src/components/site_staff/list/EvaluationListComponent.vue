<template>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column align="center" fixed label="序号" width="100">
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="学员满意度" prop="satisfactionLevel" width="200" sortable>
      <template #default="{ row }">
        <el-rate v-model="row.satisfactionLevel" disabled show-score text-color="#ff9900" score-template="{value}">
        </el-rate>
      </template>
    </el-table-column>
    <el-table-column label="评价时间" prop="evaluationTime" width="260" align="center" sortable>
      <template #default="{ row }">
        <span>{{ row.evaluationTime }}</span>
      </template>
    </el-table-column>
    <el-table-column label="学员评价" prop="comments" width="2000">
      <template #default="{ row }">
        <span>{{ row.comments.length <= 100 ? row.comments : row.comments.substring(0, 1000) + "..." }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" fixed="right" label="操作" width="160">
      <template #header>
        <el-button type="success" plain size="small" @click="showReportDialog">提交调查报告</el-button>
      </template>

      <template #default="scope">
        <el-button plain size="small" type="primary" @click="showDetailsDialog(scope.row.comments)">评价详情</el-button>
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

  <el-dialog v-model="reportDialogVisible" title="更新调查报告" width="50%">
    <span>
      <el-input
          v-model="report"
          :rows="2"
          type="textarea"
      />
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="submitReport">更新</el-button>
        <el-button type="danger" plain @click="ElMessageBox.confirm(
            '确定要关闭吗？',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
        ).then(() => {
          reportDialogVisible = false;
        }).catch(() => {
        })">关闭</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="detailsDialogVisible" title="学员评价详情" width="50%">
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

const reportDialogVisible = ref(false);
const detailsDialogVisible = ref(false);
const details = ref("");
const report = ref("");
let currentPage = ref(1);
let pageSize = ref(10);
let dataCount = ref(0);
let tableData = ref([]);

// 用于接收父组件传递的数据
const props = defineProps({
  courseId: {
    required: true
  }
});

watch(() => props.courseId, () => {
  pageSize.value = 10;
  currentPage.value = 1;
  search();
});

const showReportDialog = () => {
  if (props.courseId === undefined) {
    report.value = "";
  } else {
    axios.get(`/staff/report/${props.courseId}`).then(res => {
      if (res.data.flag) {
        report.value = res.data.data;
        ElMessage({
          message: '获取调查报告成功!',
          type: 'success',
          duration: 2 * 1000
        })
      } else {
        report.value = "";
      }
    }).catch(() => {
      report.value = "";
    });
  }

  reportDialogVisible.value = true;
};

const showDetailsDialog = (comments) => {
  details.value = comments;
  detailsDialogVisible.value = true;
};

const submitReport = () => {
  axios.put(`/staff/report/${props.courseId}`, {
    report: report.value
  }).then(res => {
    if (res.data.flag) {
      ElMessage({
        message: '提交调查报告成功!',
        type: 'success',
        duration: 2 * 1000
      })
      reportDialogVisible.value = false;
    } else {
      ElMessage({
        message: '提交调查报告失败!',
        type: 'error',
        duration: 2 * 1000
      })
    }
  }).catch(() => {
    ElMessage({
      message: '提交调查报告失败!',
      type: 'error',
      duration: 2 * 1000
    })
  })
};

const handleSizeChange = (val) => {
  pageSize.value = val;
  search();
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  search();
};

const search = () => {
  axios.get(`/staff/evaluation/${props.courseId}/${pageSize.value}/${currentPage.value}`).then(
      res => {
        if (res.data.flag) {
          ElMessage({
            message: "查询成功",
            type: "success",
            duration: 2 * 1000
          });
          tableData.value = res.data.data.data;
          dataCount.value = res.data.data.total;
          console.log(tableData.value)
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

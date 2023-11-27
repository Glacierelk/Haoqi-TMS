<template>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column align="center" fixed label="序号" width="100">
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="申请公司" prop="companyName" sortable width="260"/>
    <el-table-column align="center" label="预算" prop="budget" sortable width="150"/>
    <el-table-column align="center" label="联系方式" prop="contactInfo" width="150"/>
    <el-table-column align="center" label="申请时间" width="120">
      <template #default="{ row }">
        <span>{{ row.applicationDate.substring(0, 10) }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="申请状态" prop="status" width="100">
      <template #default="{ row }">
        <el-tag
            :type="row.status === 0 ? 'warning' : row.status === 1 ? 'success' : 'danger'"
            disable-transitions
        >{{ row.status === 0 ? '待审批' : row.status === 1 ? '已通过' : '未通过' }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column label="申请说明" prop="description" width="1000">
      <template #default="{ row }">
        <span>{{
            row.description == null ? "无" : row.description.length <= 300 ? row.description : row.description.substring(0, 300) + "..."
          }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" fixed="right" label="操作" width="230">
      <template #default="scope">
        <el-button plain size="small" type="primary" @click="showDetailsDialog(scope.row.description)">说明详情
        </el-button>

        <el-button :disabled="scope.row.status === 1" plain size="small" type="success"
                   @click="ElMessageBox.confirm(
            '确定要通过吗？',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
        ).then(() => {
          acceptApplication(scope.row);
        }).catch(() => {
        })"
        >通过
        </el-button>

        <el-button :disabled="scope.row.status === 2" plain size="small" type="danger"
                   @click="ElMessageBox.confirm(
            '确定要拒绝吗？',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
        ).then(() => {
          rejectApplication(scope.row);
        }).catch(() => {
        })">拒绝
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

  <el-dialog v-model="registerDialogVisible" title="提交报名表" width="50%">
    <span>

    </span>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="submitRegistration">提交</el-button>
        <el-button plain type="danger" @click="ElMessageBox.confirm(
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

const rejectApplication = (row) => {
  axios.put(`/manager/application/reject/${row.applicationId}`).then(
      res => {
        if (res.data.flag) {
          ElMessage({
            message: "拒绝成功",
            type: "success",
            duration: 2 * 1000
          });
          row.status = 2;
        } else {
          ElMessage({
            message: "拒绝失败",
            type: "error",
            duration: 2 * 1000
          })
        }
      }
  ).catch(() => {
    ElMessage({
      message: "拒绝失败",
      type: "error",
      duration: 2 * 1000
    })
  });
}

const acceptApplication = (row) => {
  axios.put(`/manager/application/accept/${row.applicationId}`).then(
      res => {
        if (res.data.flag) {
          ElMessage({
            message: "通过成功",
            type: "success",
            duration: 2 * 1000
          });
          row.status = 1;
        } else {
          ElMessage({
            message: "通过失败",
            type: "error",
            duration: 2 * 1000
          })
        }
      }
  ).catch(() => {
    ElMessage({
      message: "通过失败",
      type: "error",
      duration: 2 * 1000
    })
  });
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
  axios.get(`/manager/application/list/${props.queryForm.companyName}/${props.queryForm.earliestSubmitTime}/${props.queryForm.status}/${pageSize.value}/${currentPage.value}`).then(
      res => {
        if (res.data.flag) {
          // ElMessage({
          //   message: "查询成功",
          //   type: "success",
          //   duration: 2 * 1000
          // });
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

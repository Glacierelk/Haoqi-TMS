<template>

  <el-tabs v-model="activeTab" @tab-click="handleTabClick">
    <el-tab-pane label="待处理申请" name="pending"></el-tab-pane>
    <el-tab-pane label="已通过申请" name="approved"></el-tab-pane>
    <el-tab-pane label="已拒绝申请" name="rejected"></el-tab-pane>
  </el-tabs>

  <el-table :data="currentTableData" border stripe style="width: 100%" >
    <el-table-column align="center" fixed label="序号" >
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="学员姓名" prop="name"  />
    <el-table-column align="center" label="课程名称" prop="courseName"  />
    <el-table-column label="邮箱" prop="email"  align="center" sortable />
    <el-table-column label="公司" prop="companyName"  align="center" sortable />
    <el-table-column label="联系电话" prop="contactInfo"  align="center"/>
    <el-table-column align="center" fixed="right" label="操作" >
      <template #default="scope">
        <el-button plain size="large" type="success" @click="approveApplication(scope.row)" :disabled="scope.row.status === 1">接受</el-button>
        <el-button plain size="large" type="danger" @click="rejectApplication(scope.row)" :disabled="scope.row.status === 2||scope.row.status === 1">拒绝</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-pagination v-if="tableData.length > 0"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalItems"
                 style="margin-top: 15px"
  >
  </el-pagination>
</template>

<script setup>
import {ref, computed, watch} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

const originalTableData = ref([]);
const tableData = ref([]);
const pageSize = ref(10);
const currentPage = ref(1);
const totalItems = ref(tableData.value.length); // 总条目数
const activeTab = ref('pending');

// 根据当前页和分页大小计算显示的数据
const currentTableData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return tableData.value.slice(start, end);
});

const handleSizeChange = (newSize) => {
  pageSize.value = newSize;
  currentPage.value = 1; // 重置为第一页
  updateCurrentTableData();
};

const handleCurrentChange = (newPage) => {
  currentPage.value = newPage;
  updateCurrentTableData();
};

const updateCurrentTableData = () => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  currentTableData.value = tableData.value.slice(start, end);
};

const fetchAllCourseApplicationList = () => {
  axios.get('/executor/courseApplication/allApplications').then(res => {
    originalTableData.value = res.data.data;
    filterDataByTab(activeTab.value);
    totalItems.value = tableData.value.length;
  });

};

const filterDataByTab = (tabName) => {
  if (tabName === 'pending') {
    tableData.value = originalTableData.value.filter(item => item.status === 0);
  } else if (tabName === 'approved') {
    tableData.value = originalTableData.value.filter(item => item.status === 1);
  } else if (tabName === 'rejected') {
    tableData.value = originalTableData.value.filter(item => item.status === 2);
  }
  totalItems.value = tableData.value.length;
  currentPage.value = 1; // 可以重置页码到第一页
};

const approveApplication = (row) => {
  axios.get(`/executor/courseApplication/approve/${row.applicationId}`).then(res => {
    if (res.data.flag) {
      ElMessage({
        message: "审批通过",
        type: "success",
        duration: 2000
      });
      fetchAllCourseApplicationList();
    }else{
      ElMessage({
        message: res.data.errorMsg,
        type: "error",
        duration: 2000
      });
    }
  }).catch(
      err => {
        ElMessage({
          message: "error",
          type: "error",
          duration: 2000
        });
      }
  );
}

const rejectApplication = (row) => {
  axios.get(`/executor/courseApplication/reject/${row.applicationId}`, ).then(res => {
    if (res.data.flag) {
      ElMessage({
        message: "审批不通过",
        type: "warning",
        duration: 2000
      });
      fetchAllCourseApplicationList();
    }else {
      ElMessage({
        message: res.data.errorMsg,
        type: "error",
        duration: 2000
      });
    }
  }).catch(
      err => {
        ElMessage({
          message: "error",
          type: "error",
          duration: 2000
        });
      }
  );
}

watch(activeTab, (newTab) => {
  filterDataByTab(newTab);
});

fetchAllCourseApplicationList();

</script>

<style scoped>

</style>

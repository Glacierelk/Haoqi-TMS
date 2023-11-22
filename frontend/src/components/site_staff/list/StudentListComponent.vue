<template>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column align="center" fixed label="学员姓名" prop="name" width="120"/>
    <el-table-column align="center" label="性别" prop="gender" width="80">
      <template #default="scope">
        {{ scope.row.sex ? '女' : '男' }}
      </template>
    </el-table-column>
    <el-table-column
        :filter-method="attendanceFilter"
        :filters="[
          { text: '已签到', value: true },
          { text: '未签到', value: false },
        ]"
        align="center"
        filter-placement="bottom-end"
        label="签到状态"
        prop="attendance"
        width="100"
    >
      <template #default="scope">
        <el-tag
            :type="scope.row.attendance ? 'success' : 'warning'"
            disable-transitions
        >{{ scope.row.attendance ? '已签到' : '未签到' }}
        </el-tag
        >
      </template>
    </el-table-column>
    <el-table-column
        :filter-method="paymentFilter"
        :filters="[
          { text: '已缴费', value: true },
          { text: '未缴费', value: false },
        ]"
        align="center"
        filter-placement="bottom-end"
        label="缴费状态"
        prop="paid"
        width="100"
    >
      <template #default="scope">
        <el-tag
            :type="scope.row.paid ? 'success' : 'danger'"
            disable-transitions
        >{{ scope.row.paid ? '已缴费' : '未缴费' }}
        </el-tag
        >
      </template>
    </el-table-column>
    <el-table-column label="联系电话" prop="phone" width="150"/>
    <el-table-column label="邮箱" prop="email" width="200"/>
    <el-table-column label="公司名称" prop="companyName" width="1000"/>
    <el-table-column align="center" fixed="right" label="操作" width="160">
      <template #default="scope">
        <el-button :disabled="scope.row.attendance === true" plain size="small" type="primary"
                   @click="signIn(scope.row)">签到
        </el-button>
        <el-button :disabled="scope.row.paid === true" plain size="small" type="success" @click="pay(scope.row)">缴费
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
      style="margin-top: 20px; text-align: right;"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
  />
</template>

<script setup>

import {ElMessage, ElMessageBox} from "element-plus";
import {defineProps, reactive, ref, watch} from 'vue';
import axios from "axios";

let tableData = ref([]);
let dataCount = ref(100);
let pageSize = ref(10);
let currentPage = ref(1);
// let queryForm = ref({});

// 用于接收父组件传递的数据
const props = defineProps({
  query: {
    type: Object,
    required: true
  }
});

watch(() => props.query, () => {
  init();
});

const signIn = (row) => {

  ElMessageBox.confirm('确认签到?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {

    axios.put(`/staff/attendance/${row.studentId}/${row.courseId}/true`).then((res) => {
      if (res.data.flag) {
        row.attendance = true;
        ElMessage({
          message: '签到成功!',
          type: 'success',
          duration: 2 * 1000
        });
      } else {
        ElMessage({
          message: '签到失败!',
          type: 'error',
          duration: 2 * 1000
        });
      }
    }).catch(() => {
      ElMessage({
        message: '签到失败!',
        type: 'error',
        duration: 2 * 1000
      });
    });

  }).catch(() => {
    ElMessage({
      message: '签到已取消!',
      type: 'warning',
      duration: 2 * 1000
    });
  });

}

const pay = (row) => {

  ElMessageBox.confirm('确认缴费?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {

    axios.put(`/staff/payment/${row.studentId}/${row.courseId}`).then((res) => {
      if (res.data.flag) {
        row.paid = true;
        ElMessage({
          message: '缴费成功!',
          type: 'success',
          duration: 2 * 1000
        });
      } else {
        ElMessage({
          message: '缴费失败!',
          type: 'error',
          duration: 2 * 1000
        });
      }
    }).catch(() => {
      ElMessage({
        message: '缴费失败!',
        type: 'error',
        duration: 2 * 1000
      });
    });

  }).catch(() => {
    ElMessage({
      message: '缴费已取消!',
      type: 'warning',
      duration: 2 * 1000
    });
  });
}

const attendanceFilter = (value, row) => {
  return row.attendance === value;
}

const paymentFilter = (value, row) => {
  return row.paid === value;
}

const handleSizeChange = (val) => {
  pageSize.value = val;
  currentPage.value = 1;
  search();
}

const handleCurrentChange = (val) => {
  currentPage.value = val;
  search();
}

const search = () => {
  axios.get(`/staff/students_list/${props.query.courseId}/${props.query.name}/${props.query.phone}/${props.query.companyName}/${pageSize.value}/${currentPage.value}`)
      .then(res => {
        if (res.data.flag) {
          // ElMessage({
          //   message: '查询成功!',
          //   type: 'success',
          //   duration: 2 * 1000
          // });
          tableData.value = res.data.data;
        } else {
          ElMessage({
            message: '查询失败!',
            type: 'error',
            duration: 2 * 1000
          });
        }
      })
      .catch(() => {
        ElMessage({
          message: '查询失败!',
          type: 'error',
          duration: 2 * 1000
        });
      });
}

const getCount = () => {
  axios.get(`/staff/students_list/${props.query.courseId}/${props.query.name}/${props.query.phone}/${props.query.companyName}/0/0`)
      .then(res => {
        if (res.data.flag) {
          dataCount.value = res.data.data;
        }
      });
}

async function init() {
  await getCount();
  search();
}

init();


</script>

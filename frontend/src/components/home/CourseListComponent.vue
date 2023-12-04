<template>
  <el-table :data="tableData" border stripe style="width: 100%">
    <el-table-column align="center" fixed label="序号" width="100">
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="课程名称" prop="name" width="200" />
    <el-table-column label="授课教师" prop="instructorName" width="150" align="center" sortable />
    <el-table-column label="签约公司" prop="companyName" width="260" align="center" sortable />
    <el-table-column label="课程时间" width="200" align="center">
      <template #default="{ row }">
        <span>{{ row.startDate.substring(0, 10) }} 至 {{ row.endDate.substring(0, 10) }}</span>
      </template>
    </el-table-column>
    <el-table-column label="课程介绍" prop="description" width="1000">
      <template #default="{ row }">
        <span>{{ row.description != null ? (row.description.length <= 300 ? row.description : row.description.substring(0, 300) + "...") : "无" }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" fixed="right" label="操作" width="180">
      <template #default="scope">
        <el-button plain size="small" type="primary" @click="showDetailsDialog(scope.row.description)">介绍详情</el-button>
        <el-button plain size="small" type="success" @click="showRegisterDialog(scope.row)">报名</el-button>
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

  <el-dialog v-model="registerDialogVisible" title="提交报名表" width="40%" draggable="draggable" :show-close="false">
    <div>
      <el-form :model="registerForm" label-width="80px">
        <!-- 第一行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名" required>
              <el-input v-model="registerForm.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" required>
              <el-radio-group v-model="registerForm.gender">
                <el-radio label=1>男</el-radio>
                <el-radio label=0>女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第二行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="邮箱" required>
              <el-input v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系方式" required>
              <el-input v-model="registerForm.contactInfo" placeholder="请输入联系方式"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 单独一行的公司名称 -->
        <el-form-item label="公司名称">
          <el-select v-model="registerForm.companyName" placeholder="请选择公司名称" filterable clearable>
            <el-option
                v-for="company in companyList"
                :key="company"
                :label="company"
                :value="company">
            </el-option>
          </el-select>
        </el-form-item>

        <!-- 第三行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="团报码">
              <el-input v-model="registerForm.promoCode" placeholder="如果有团报码，请输入"
                :disabled="registerForm.companyName.trim() === ''" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工作岗位">
              <el-input v-model="registerForm.jobPosition" placeholder="请输入工作岗位"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第四行 -->
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="技术水平">
              <el-input v-model="registerForm.skillLevel" placeholder="请描述您的技术水平"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </div>

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
import { defineProps, ref, watch } from 'vue';
import axios from "axios";

const registerDialogVisible = ref(false);
const registerForm = ref({
  name: '',
  gender: '',
  email: '',
  contactInfo: '',
  companyName: '',
  promoCode: '',
  jobPosition: '',
  skillLevel: ''
});

let currentPage = ref(1);
let pageSize = ref(10);
let dataCount = ref(0);
let tableData = ref([]);
let detailsDialogVisible = ref(false);
let details = ref("");
let courseId = ref("");
let courseName = ref("");
const companyList = ref([]);
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

// 查询公司列表
const fetchCompanyList = async () => {
  try {
    const response = await axios.get('/home/companyNames'); // 修改为实际的API路径
    companyList.value = response.data.data;
    // console.log('公司名称列表', companyList.value);
  } catch (error) {
    console.error('获取公司名称列表失败', error);
  }
};

const resetRegisterForm = () => {
  registerForm.value = {
    name: '',
    gender: '',
    email: '',
    contactInfo: '',
    companyName: '',
    promoCode: '',
    jobPosition: '',
    skillLevel: ''
  };
}

const submitRegistration = (row) => {
  registerDialogVisible.value = true;
  axios.post(`/home/createCourseApplication`, {
    "courseId": courseId.value,
    "courseName": courseName.value,
    "name": registerForm.value.name,
    "gender": registerForm.value.gender,
    "email": registerForm.value.email,
    "contactInfo": registerForm.value.contactInfo,
    "companyName": registerForm.value.companyName,
    "promoCode": registerForm.value.promoCode,
    "jobPosition": registerForm.value.jobPosition,
    "skillLevel": registerForm.value.skillLevel
  }).then(
      res => {
        // console.log(res);
        if (res.data.flag) {
          ElMessage({
            message: "报名成功",
            type: "success",
            duration: 2000
          });
          registerDialogVisible.value = false;
        } else {
          ElMessage({
            message: res.data.errorMsg,
            type: "error",
            duration: 2000
          });
        }
      }
  ).catch(
      err => {
        ElMessage({
          message: "报名失败",
          type: "error",
          duration: 2000
        });
      }
  );

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
  courseName.value = row.name;
  resetRegisterForm();
  registerDialogVisible.value = true;
}

const search = () => {
  axios.get(`/home/courses/${props.queryForm.courseName}/${props.queryForm.instructorName}/${props.queryForm.companyName}/${props.queryForm.earliestStartTime}/${pageSize.value}/${currentPage.value}`).then(
      res => {
        if (res.data.flag) {
          // ElMessage({
          //   message: "查询成功",
          //   type: "success",
          //   duration: 2 * 1000
          // });
          dataCount.value = res.data.data.total;
          tableData.value = res.data.data.data;
          console.log('课程列表', tableData.value)
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
fetchCompanyList()

</script>

<style scoped>

.autowrap {
  width: 95%;
  word-wrap: break-word; /* 旧的浏览器版本 */
  overflow-wrap: break-word; /* 现代的浏览器版本 */
}

</style>

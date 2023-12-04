<template>

  <div class="tab-list-div">
    <a-tabs
        v-model:activeKey="activeKey"
        type="card"
        size="large"
    >
      <a-tab-pane key="1" tab="当前课程列表">
        <BrowseTrainingNotificationsComponent/>
      </a-tab-pane>

      <template #rightExtra>
        <a-button type="primary" @click="showApplicationDialog" style="margin-right: 10px;">提交培训申请</a-button>

        <a-button type="primary" @click="login">登陆</a-button>
      </template>
    </a-tabs>
  </div>

  <el-dialog v-model="applicationDialogVisible" title="软件公司培训申请表" width="40%" draggable="draggable"
             :show-close="false">
    <div>
      <el-form :model="registerForm" label-width="80px">
        <!-- 第一行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="公司名称" required>
              <el-input v-model="registerForm.companyName" placeholder="请输入公司名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="团报码" required>
              <el-input v-model="registerForm.promoCode" placeholder="请指定贵公司团报码"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第二行 -->
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="预算" required>
              <el-input v-model="registerForm.budget" placeholder="请输入培训总预算"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系信息" required>
              <el-input v-model="registerForm.contactInfo" placeholder="请输入联系信息" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 第三行 -->
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="描述信息">
              <el-input v-model="registerForm.description" placeholder="请输入培训的相关信息" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>


    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="submitRegistrationForm">提交</el-button>
        <el-button type="danger" plain @click="ElMessageBox.confirm(
            '确定要关闭吗？',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
        ).then(() => {
          applicationDialogVisible = false;
        }).catch(() => {
        })">关闭</el-button>
      </span>
    </template>
  </el-dialog>

</template>

<script setup>

import {useRouter} from "vue-router";
import {ref} from 'vue';
import BrowseTrainingNotificationsComponent from "@/components/home/BrowseTrainingNotificationsComponent.vue";
import {ElMessage, ElMessageBox} from "element-plus";
import axios from "axios";

const activeKey = ref('1');
const router = useRouter();
const applicationDialogVisible = ref(false);

const registerForm = ref({
  companyName: '',
  promoCode: '',
  budget: '',
  contactInfo: '',
  description: '',
});

const resetRegisterForm = () => {
  registerForm.value = {
    companyName: '',
    promoCode: '',
    budget: '',
    contactInfo: '',
    description: '',
  };
};

function login() {
  router.push({name: 'login'});
}

const submitRegistrationForm = () => {
  axios.post('/home/createTrainingApplication', {
    "companyName": registerForm.value.companyName,
    "promoCode": registerForm.value.promoCode,
    "budget": registerForm.value.budget,
    "contactInfo": registerForm.value.contactInfo,
    "description": registerForm.value.description,
  }).then(
      res => {
        if (res.data.flag) {
          ElMessage({
            message: "提交培训申请成功",
            type: "success",
            duration: 2000
          });
          applicationDialogVisible.value = false;
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
          message: "提交培训申请失败",
          type: "error",
          duration: 2000
        });
      }
  );
};

const showApplicationDialog = () => {
  resetRegisterForm();
  applicationDialogVisible.value = true;
};

</script>

<style scoped>

.tab-list-div {
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

</style>

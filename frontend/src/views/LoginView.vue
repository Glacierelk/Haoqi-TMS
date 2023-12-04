<template>
  <div id="login">
    <div class="container">
      <el-form class="form-wrap" label-width="60px" @submit.native.prevent="submitForm">
        <h2>登录</h2>
        <el-form-item class="label" label="用户名" prop="username">
          <el-input v-model.trim="username" placeholder="请输入用户名" clearable required />
        </el-form-item>
        <el-form-item class="label" label="密码" prop="password">
          <el-input required v-model.trim="password" placeholder="请输入密码" clearable show-password type="password" />
        </el-form-item>
        <el-form-item>
          <el-button native-type="submit" type="primary">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";

axios.defaults.withCredentials = false;
import {ref} from "vue";
import {ElForm, ElFormItem, ElInput, ElButton} from "element-plus";
import {useRouter} from "vue-router";

const router = useRouter();
const username = ref("");
const password = ref("");
import {ElMessage} from "element-plus";

function submitForm() {

  if (username.value === "" || password.value === "") {

    ElMessage({
      message: '用户名或密码不能为空!',
      type: 'error',
      duration: 2 * 1000
    });
    return false;
  }

  axios.post('/user/login', {
    "username": username.value,
    "password": password.value,
  })
      .then((res) => {
        console.log(res.data)
        if (res.data.flag) {
          ElMessage({
            message: '登录成功!',
            type: 'success',
            duration: 2 * 1000
          });
          switch (res.data.data.userType) {
            case 0:
              router.push('/manager');
              break;
            case 1:
              router.push('/executor');
              break;
            case 2:
              // TODO 跳转到教师界面
              // router.push('/student');
              break;
            case 3:
              router.push('/staff')
              break;
            case 4:
              // TODO admin
              // router.push('/manager')
              break;
            case 5:
              // TODO 跳转到学员界面
              break;
          }
        } else {
          ElMessage({
            message: '登录失败，请重试!',
            type: 'error',
            duration: 2 * 1000
          });
          username.value = "";
          password.value = "";
        }
      })
      .catch(() => {
        ElMessage({
          message: '登录失败，请重试!',
          type: 'error',
          duration: 2 * 1000
        });
        username.value = "";
        password.value = "";
      });

  return false;
}

</script>

<style scoped>

#login {
  height: 100vh;
  width: 100%;
  background-image: url("../assets/Big Sur Graphic_1.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  overflow: hidden;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.form-wrap {
  width: 400px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  padding: 40px;
}

h2 {
  margin-bottom: 16px;
}

.el-form-item__content > .el-input {
  width: 100%;
}

.el-button--primary,
.el-button--default {
  width: 100%;
  margin-top: 10px;
}

.el-button--primary {
  background-color: #409eff;
  border-color: #409eff;
}

.el-button--primary:hover {
  background-color: #66b1ff;
  border-color: #66b1ff;
}

.el-button--default:hover {
  background-color: #f5f5f5;
}

.register {
  margin-top: 16px;
  text-align: right;
}

.label {
  font-weight: bold;
}

</style>
<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        讲师主页
      </el-header>
      <el-container>
        <el-aside width="400px">
          <el-card>
            <div>
              <span>个人简历</span>
            </div>
            <div class="name-role">
              <span class="sender">{{instructorData.name}}</span>
            </div>
            <div class="registe-info">
          <span class="registe-info">
            职称  {{instructorData.title}}
          </span>
            </div>
            <el-divider></el-divider>
            <div class="personal-relation">
              <div class="relation-item">
                手机号:  <div style="float: right; padding-right:20px;">{{instructorData.contactInfo}}</div>
              </div>
            </div>
            <div class="personal-relation">
              <div class="relation-item">
                Email:  <div style="float: right; padding-right:20px;">{{instructorData.email}}</div>
              </div>
            </div>
            <div class="personal-relation">
              <div class="relation-item" >
                擅长的领域: <div style="float: right; padding-right:0px; color: #409eff" type="text" @click="showFullText">
                {{ instructorData.expertiseArea ? (instructorData.expertiseArea.length > 20 ? instructorData.expertiseArea.substring(0, 20) + '...' : instructorData.expertiseArea) : '' }}
<!--                {{ instructorData.expertiseArea.substring(0, 20) + '...'}}-->
<!--                {{ instructorData.expertiseArea}}-->
              </div>
              </div>
            </div>
          </el-card>
        </el-aside>
        <el-main>
          <el-scrollbar>
            <el-table :data="courseData">
              <el-table-column fixed prop="name" label="课程名称" width="140" />
              <el-table-column prop="companyName" label="公司名称" width="140" />
              <el-table-column prop="description" label="课程描述" width="300" show-overflow-tooltip />
              <el-table-column prop="startDate" label="开始时间" width="140" />
              <el-table-column prop="endDate" label="结束时间" width="140" />
              <el-table-column prop="location" label="上课地点" width="140" />
              <el-table-column prop="courseFee" label="课程费用" width="140" />
            </el-table>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { ref,  watch } from 'vue';
import { useRoute } from 'vue-router';
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";

const router = useRoute();
const courseData = ref([])
const instructorData=ref([])
const executorId = ref(router.query.executorId).value;
console.log("executorId"+executorId)

const getTeacherCourseList = (teacherId,courseList) => {
  console.log("teacherId"+teacherId)
  axios.get(`/instructor/course/yourCourse/${teacherId}`).then(
      response => {
        //console.log("数据库"+response.data.data.data);
        courseList.value = response.data.data;
      },
      response => {
        console.log("error");
        alert("请求失败");
      }
  );
}
const getTeacherInformationList = (teacherId,instructorList) => {
  axios
      .get(`/instructor/my/infomation/${teacherId}`)
      .then(
          response => {
            instructorList.value = response.data.data;
            console.log("courseList:",response.data.data.expertiseArea);
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
      );
}

const showFullText = () => {
  ElMessageBox.confirm(instructorData.value.expertiseArea, {
    confirmButtonText: '确定',
    showCancelButton: false, // 设置取消按钮不显示
  });
}

getTeacherCourseList(executorId,courseData);
getTeacherInformationList(executorId,instructorData);

</script>

<style scoped>
.common-layout {
  background-color: #f0f2f5; /* 设置背景色 */
  padding: 20px; /* 设置整体内边距 */
}

.el-header {
  font-weight: bold;
  font-size: 1.5em;
  background-color: #409eff; /* 设置头部背景色 */
  color: #fff; /* 设置头部文字颜色 */
  padding: 10px; /* 设置头部内边距 */
  text-align: center; /* 文字居中 */
}

.el-aside {
  background-color: #fff; /* 设置侧边栏背景色 */
  padding: 20px; /* 设置侧边栏内边距 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
}

.el-card {
  border: 1px solid #ebeef5; /* 添加卡片边框 */
  border-radius: 5px; /* 添加卡片圆角 */
  overflow: hidden; /* 隐藏卡片内容溢出部分 */
}

.name-role {
  font-size: 18px; /* 放大字体 */
  padding: 10px;
  text-align: center;
  color: #333; /* 设置文字颜色 */
}

.sender {
  font-weight: bold; /* 加粗文字 */
}

.registe-info {
  text-align: center;
  padding-top: 10px;
  color: #666; /* 设置次要信息颜色 */
}

.personal-relation {
  font-size: 16px;
  padding: 10px;
  margin-bottom: 15px; /* 调整底部间距 */
  background-color: #f5f7fa; /* 设置背景色 */
  border: 1px solid #ebeef5; /* 添加边框 */
  border-radius: 5px; /* 添加圆角 */
}

.relation-item {
  padding: 12px;
}

.el-main {
  padding: 20px;
}

.el-scrollbar {
  max-height: 400px; /* 设置滚动区域最大高度 */
  overflow: auto; /* 添加滚动条 */
}

.ellipsis {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>

<template>
  <el-table :data="currentCourseList" border stripe style="width: 100%">
    <el-table-column align="center" fixed label="序号">
      <template #default="{ $index }">
        <span>{{ $index + 1 }}</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="课程名称" prop="name"/>
    <el-table-column align="center" label="开始日期" prop="startDate"/>
    <el-table-column align="center" label="结束日期" prop="endDate"/>
    <el-table-column align="center" label="讲师名称" prop="instructorName"/>
    <el-table-column align="center" fixed="right" label="操作">
      <template #default="scope">
        <el-button plain size="large" type="primary" :disabled="isBeforeNow(scope.row.endDate)" @click="showEvaluationDialog(scope.row)">评价</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-pagination v-if="originCourseList.length > 0"
                 @size-change="handleSizeChange"
                 @current-change="handleCurrentChange"
                 :current-page="currentPage"
                 :page-sizes="[10, 20, 30, 40]"
                 :page-size="pageSize"
                 layout="total, sizes, prev, pager, next, jumper"
                 :total="totalItems"
                 style="margin-top: 15px">
  </el-pagination>

  <el-dialog v-model="evaluationDialogVisible" title="培训课程评价" width="40%" draggable="draggable" :show-close="false">
    <div>
      <el-form :model="evaluationForm" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="满意度">
              <el-rate
                  v-model="evaluationForm.satisfactionLevel"
                  :max="5"
              ></el-rate>
            </el-form-item>
            <el-form-item label="评价内容">
              <el-input v-model="evaluationForm.comments" placeholder="请输入评价内容" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="submitEvaluation">提交</el-button>
        <el-button type="danger" plain @click="ElMessageBox.confirm(
            '确定要关闭吗？',
            '提示',
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
        ).then(() => {
          evaluationDialogVisible = false;
        }).catch(() => {
        })">关闭</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref, computed, watch} from "vue";
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";

const pageSize = ref(10);
const currentPage = ref(1);
const originCourseList = ref([]);
const totalItems = ref(0); // 总条目数
const currentCourseList = ref([]);
const evaluationDialogVisible = ref(false);
const studentId = ref(81);
const courseId = ref();
const evaluationForm = ref({
  satisfactionLevel: 0,
  comments: ""
});

const isBeforeNow = (endDateString) => {

  const endDate = new Date(endDateString);
  const now = new Date();
  return endDate > now; // 如果课程结束时间在当前时间之前，返回 true
};

const resetEvaluationForm = () => {
  evaluationForm.value.satisfactionLevel = 0;
  evaluationForm.value.comments = "";
};
const showEvaluationDialog = (row) => {
  evaluationDialogVisible.value = true;
  courseId.value = row.courseId;
};

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
  currentCourseList.value = originCourseList.value.slice(start, end);
};

const fetchCourseList = () => {
  axios.get(`/student/getCourseList/${studentId.value}`).then(res => {
    if (res.data.flag) {
        originCourseList.value = res.data.data;
        totalItems.value = originCourseList.value.length;
        updateCurrentTableData();
    }else{
      ElMessage({
        message: res.data.message,
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

const submitEvaluation = () => {
  axios.post(`/student/createTrainingEvaluation`,{
    "studentId": studentId.value,
    "courseId": courseId.value,
    "satisfactionLevel": evaluationForm.value.satisfactionLevel,
    "comments": evaluationForm.value.comments
  }).then(res => {
    if (res.data.flag) {
      ElMessage({
        message: "评价成功",
        type: "success",
        duration: 2000
      });
      evaluationDialogVisible.value = false;
      resetEvaluationForm();
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

fetchCourseList();
</script>


<style scoped>

</style>
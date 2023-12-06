<template>
  <el-card class="box-card">
    <el-row :gutter="24">
      <el-col :span="12">
        <el-input placeholder="请输入内容" v-model="queryInfo.query">
          <template #append>
            <el-button @click="getData">
              搜索
            </el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="3">
        <el-button type="primary" plain @click="exportStudentEmail">导出全部学员邮箱</el-button>
      </el-col>
      <el-col :span="3">
        <el-button type="primary" plain @click="exportAllStudents">导出全部学员信息</el-button>
      </el-col>
      <el-col :span="3">
        <el-button type="primary" plain @click="downloadTemplate">下载批量导入模板</el-button>
      </el-col>
      <el-col :span="3">
        <el-upload
            :limit="1"
            :show-file-list="false"
            :auto-upload="false"
            :on-change="uploadStudent"
            accept=".xlsx"
            ref="uploadFile"
        >
          <el-button type="success" plain>批量导入学员</el-button>
        </el-upload>
      </el-col>
<!--      <div style="text-align: right; margin: 0">-->
<!--        <el-button type="success"  float:right @click="exportStudentEmail">导出全部学生邮箱</el-button>-->
<!--      </div>-->
    </el-row>

    <el-table :data="tableData" border stripe class="table-with-margin" style="width: 100% " @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center"></el-table-column>-->
      <el-table-column prop="studentId" label="学号" align="center"></el-table-column>
      <el-table-column prop="name" label="姓名" align="center"></el-table-column>
      <el-table-column prop="gender" label="性别" align="center" width="70px">
         <template v-slot="scope">
           {{scope.row.gender === 0 ? '男' : '女'}}
         </template>
      </el-table-column>
      <el-table-column prop="email" label="Email" align="center"></el-table-column>
      <el-table-column prop="companyName" label="公司名称" align="center"></el-table-column>
      <el-table-column prop="jobPosition" label="工作岗位" align="center"></el-table-column>
      <el-table-column prop="skillLevel" label="技术水平" align="center"></el-table-column>
      <el-table-column prop="contactInfo" label="联系方式" align="center"></el-table-column>
<!--      <el-table-column label="操作" align="center" width="200px">-->
<!--        <template v-slot="scope">-->
<!--          <el-button @click="openChangeCourse(scope.row)" type="primary" >修改</el-button>-->
<!--          <el-button @click="removeData(scope.row)" type="danger" >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
    <el-dialog v-model="addDialogVisible" title="添加学员" width="40%"  >
      <!-- 内容主体区域 -->
      <el-form ref="addFormRef" :model="addForm" label-width="70px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="gender">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="addForm.gender"></el-input>
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="addForm.companyName"></el-input>
        </el-form-item>
        <el-form-item label="工作岗位" prop="jobPosition">
          <el-input v-model="addForm.jobPosition"></el-input>
        </el-form-item>
        <el-form-item label="技术水平" prop="skillLevel">
          <el-input v-model="addForm.skillLevel"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactInfo">
          <el-input v-model="addForm.contactInfo"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
<!--      <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="addDialogVisible = false">取消</el-button>-->
<!--        <el-button type="primary" @click="handleAdd">添加学员</el-button>-->
<!--      </span>-->
<!--      </template>-->
    </el-dialog>
    <el-dialog v-model="changeDialogVisible" title="修改学员" width="40%"  >
      <!-- 内容主体区域 -->
      <el-form ref="changeFormRef" :model="changeForm" label-width="70px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="changeForm.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="changeForm.gender"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="gender">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="changeForm.companyName"></el-input>
        </el-form-item>
        <el-form-item label="工作岗位" prop="jobPosition">
          <el-input v-model="changeForm.jobPosition"></el-input>
        </el-form-item>
        <el-form-item label="技术水平" prop="skillLevel">
          <el-input v-model="changeForm.skillLevel"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactInfo">
          <el-input v-model="changeForm.contactInfo"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
<!--      <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="changeDialogVisible = false">取消</el-button>-->
<!--        <el-button type="primary" @click="changeInstructor">修改</el-button>-->
<!--      </span>-->
<!--      </template>-->
    </el-dialog>
  </el-card>
</template>

<script>
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";

export default {
  data() {
    return {
      queryInfo: {
        query: '', // 查询参数
      },
      tableData: [], //存放从后端传来的数据
      addDialogVisible: false,
      changeDialogVisible:false,
      addForm: {
        studentId: '',
        name: '',
        gender: '',
        email: '',
        companyName: '',
        jobPosition: '',
        skillLevel: '',
        contactInfo: ''
      },
      changeForm: {
        studentId: '',
        name: '',
        gender: '',
        email: '',
        companyName: '',
        jobPosition: '',
        skillLevel: '',
        contactInfo: ''
      },
    };
  },
  mounted() {
    this.getData()
  },
  methods: {
    // 导出全部学员信息
    exportAllStudents() {
      axios({
        url: `/executor/student/export`,
        method: 'GET',
        responseType: 'arraybuffer', // 设置响应数据类型为 arraybuffer
      })
          .then(response => {
            const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' }); // 创建一个 Blob 对象
            const url = window.URL.createObjectURL(blob); // 创建一个 URL 对象
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', '学员.xlsx'); // 设置下载文件名，确保是 Excel 格式（.xlsx）
            document.body.appendChild(link);
            link.click(); // 模拟点击链接进行下载
            link.parentNode.removeChild(link); // 下载完成后移除元素
          })
          .catch(error => {
            // 处理下载失败的情况
            // console.error('下载失败', error);
            ElMessage.error('下载失败');
          });
    },
    uploadStudent(file) {
      const formData = new FormData();
      formData.append('students', file.raw);

      axios.post('/executor/student/import', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
          .then(response => {
            if (response.data.flag) {
              ElMessage.success('上传成功');
            } else {
              ElMessage.error(response.data.errorMsg);
            }
          })
          .catch(() => {
            ElMessage.error('上传失败');
          });
      this.$refs.uploadFile.clearFiles()
    },
    downloadTemplate() {
      axios({
        url: `/executor/student/template`,
        method: 'GET',
        responseType: 'arraybuffer', // 设置响应数据类型为 arraybuffer
      })
          .then(response => {
            const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' }); // 创建一个 Blob 对象
            const url = window.URL.createObjectURL(blob); // 创建一个 URL 对象
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', '学员批量导入模板.xlsx'); // 设置下载文件名，确保是 Excel 格式（.xlsx）
            document.body.appendChild(link);
            link.click(); // 模拟点击链接进行下载
            link.parentNode.removeChild(link); // 下载完成后移除元素
          })
          .catch(error => {
            // 处理下载失败的情况
            // console.error('下载失败', error);
            ElMessage.error('下载失败');
          });
    },
    //获取后端数据
    getData() {
      axios.get("/executor/student/getAll").then(
              response => {
                this.tableData = response.data.data;
              },
              response => {
                console.log("error");
                alert("请求失败");
              }
          );
    },
    exportStudentEmail(){
      axios({
        url: `/executor/student/email/download`,
        method: 'GET',
        responseType: 'arraybuffer', // 设置响应数据类型为 arraybuffer
      })
          .then(response => {
            const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' }); // 创建一个 Blob 对象
            const url = window.URL.createObjectURL(blob); // 创建一个 URL 对象
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', '学生邮箱.xlsx'); // 设置下载文件名，确保是 Excel 格式（.xlsx）
            document.body.appendChild(link);
            link.click(); // 模拟点击链接进行下载
            link.parentNode.removeChild(link); // 下载完成后移除元素
          })
          .catch(error => {
            // 处理下载失败的情况
            // console.error('下载失败', error);
            ElMessage.error('下载失败');
          });
    },
    //打开修改框的时候把原来的数据填上
    openChangeCourse(row){
      this.changeForm.studentId=row.studentId;
      this.changeForm.name=row.name;
      this.changeForm.gender=row.gender;
      this.changeForm.email=row.email;
      this.changeForm.companyName=row.companyName;
      this.changeForm.jobPosition=row.jobPosition;
      this.changeForm.skillLevel=row.skillLevel;
      this.changeForm.contactInfo=row.contactInfo;
      this.changeDialogVisible = true;
    },
    //修改学员信息
    changeInstructor() {
      console.log("changeForm" + this.changeForm.name)
      axios
          .post("/executor/student/update", this.changeForm)
          .then((response) => {
            this.$message.success("修改成功");
            // 添加成功后刷新数据
            this.getUserList();
            // 关闭添加对话框
            this.changeDialogVisible = false;
          })
          .catch((error) => {
            console.error("修改失败", error);
            this.$message.error("修改失败");
          });
      this.changeDialogVisible = false;
    },
    //添加学员
    handleAdd(){
      // 在这里执行编辑操作，例如提交表单数据到后端
      axios
          .post("/executor/student/create", this.addForm)
          .then((response) => {
            this.$message.success("添加成功");
            // 添加成功后刷新数据
            this.getData();
            // 关闭添加对话框
            this.addDialogVisible = false;
          })
          .catch((error) => {
            console.error("添加失败", error);
            this.$message.error("添加失败");
          });
      // 关闭编辑对话框
      this.addDialogVisible = false;
    },
    //删除信息
    removeData(row) {
      // 弹出确认对话框，确认后执行删除操作
      ElMessageBox.confirm('确定删除'+row.name+'信息吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 发送删除请求
        axios.delete(`url`)
            .then(response => {
              this.$message.success('删除成功');
              // 删除成功后刷新数据
              this.getData();
            })
            .catch(error => {
              console.error("删除失败", error);
              this.$message.error('删除失败');
            });
      }).catch(() => {
        // 用户取消删除操作
      });
    },
  },
}
</script>

<style scoped>
.table-with-margin {
  margin-top: 20px; /* 调整表格与上方内容的间距，根据实际需要设置 */
}

</style>
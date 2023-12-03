<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <!-- 搜索与添加区域 -->
          <!--          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getUserList">-->
          <el-input placeholder="请输入内容" v-model="queryInfo.query">
            <template #append>
              <el-button @click="getUserList(null)">
                搜索
              </el-button>
            </template>

          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="openApprovalTraining">添加课程</el-button>
        </el-col>
      </el-row>
      <!-- 公司列表区域  -->
      <el-table :data="courserList" border stripe class="table-with-margin">
        <el-table-column type="index"></el-table-column>
        <el-table-column label="课程名称" prop="name"></el-table-column>
        <el-table-column label="公司名称" prop="companyName"></el-table-column>
        <el-table-column label="描述" prop="description" show-overflow-tooltip></el-table-column>
        <el-table-column label="开始时间" prop="startDate"></el-table-column>
        <el-table-column label="结束时间" prop="endDate" width="170px"></el-table-column>
        <el-table-column label="地点" prop="location"></el-table-column>
        <el-table-column label="课程费用" prop="courseFee"></el-table-column>
        <el-table-column label="操作" width="260px">
          <template v-slot="scope">
            <!-- 修改按钮 -->
            <el-button type="primary"  size="mini" @click="openChangeCourse(scope.row)">修改</el-button>
            <!-- 删除按钮 -->
            <el-button type="danger"  size="mini" @click="removeData(scope.row)">删除</el-button>
            <!-- 查看所授课程按钮 -->
            <el-button type="success"  size="mini" @click="searchInstructor">查看讲师</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 展示审批通过的内容的区域  -->
      <el-dialog v-model="approvalDialogVisible" title="待创建内容" width="70%">
        <el-table :data="approvalList" border stripe class="table-with-margin">
          <el-table-column type="index"></el-table-column>
          <el-table-column label="公司名称" prop="companyName"></el-table-column>
          <el-table-column label="预算" prop="budget"></el-table-column>
          <el-table-column label="联系方式" prop="contactInfo"></el-table-column>
          <el-table-column label="操作" width="300px">
            <template v-slot="scope">
              <!-- 修改按钮 -->
              <el-button type="primary"  size="mini" @click="openAddCourse(scope.row)">创建</el-button>
              <!-- 删除按钮 -->
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>

      <el-dialog v-model="addDialogVisible" title="添加课程" width="40%" :z-index="100" >
        <!-- 内容主体区域 -->
        <el-form ref="addFormRef" :model="addForm"  label-width="70px">
          <el-form-item label="课程名称" prop="name">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
          <el-form-item label="公司名称" prop="companyName">
            <el-input v-model="addForm.companyName"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="description">
            <el-input v-model="addForm.description"></el-input>
          </el-form-item>
          <el-form-item label="开始时间" prop="startDate">
            <a-date-picker  v-model:value="addForm.startDate"  style="width: 100%" append-to-body="true" />
          </el-form-item>
          <el-form-item label="结束时间" prop="endDate">
            <a-date-picker  v-model:value="addForm.endDate" style="width: 100%" append-to-body="true"/>
          </el-form-item>
          <el-form-item label="上课地点" prop="location">
            <el-input v-model="addForm.location"></el-input>
          </el-form-item>
          <el-form-item label="课程费用" prop="courseFee">
            <el-input v-model="addForm.courseFee"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部区域 -->
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addCourse">新建</el-button>
      </span>
        </template>
      </el-dialog>

      <!-- 修改用户的对话框 -->
      <el-dialog v-model="changeDialogVisible" title="修改课程" width="40%" >
        <!-- 内容主体区域 -->
        <el-form ref="changeFormRef" :model="changeForm"  label-width="70px">
          <el-form-item label="课程名称" prop="name">
            <el-input v-model="changeForm.name"></el-input>
          </el-form-item>
          <el-form-item label="公司名称" prop="companyName">
            <el-input v-model="changeForm.companyName"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="description">
            <el-input v-model="changeForm.description"></el-input>
          </el-form-item>
          <el-form-item label="开始时间" prop="startDate">
            <el-input v-model="changeForm.startDate"></el-input>
          </el-form-item>
          <el-form-item label="结束时间" prop="endDate">
            <el-input v-model="changeForm.endDate"></el-input>
          </el-form-item>
          <el-form-item label="上课地点" prop="location">
            <el-input v-model="changeForm.location"></el-input>
          </el-form-item>
          <el-form-item label="课程费用" prop="courseFee">
            <el-input v-model="changeForm.courseFee"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部区域 -->
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="changeDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="changeCourse">修改</el-button>
      </span>
        </template>
      </el-dialog>
    </el-card>
  </div>
</template>

<script >
import axios from "axios";
import {ElMessage, ElMessageBox} from "element-plus";

export default {
  data () {
    return {
      // 获取用户列表的参数对象
      queryInfo: {
        query: '', // 查询参数
        // pagenum: 1, // 当前页码
        // pagesize: 10 // 每页显示条数
      },
      // 用户列表
      courserList: [],
      approvalList:[],
      // 总数据条数
      // total: 0,
      // 控制添加用对话框的显示和隐藏，默认false,表示隐藏对话框
      addDialogVisible: false,
      changeDialogVisible:false,
      approvalDialogVisible:false,
      // 添加用户的表单数据对象
      addForm: {
        courseId:'',
        name: '',
        companyName: '',
        description: '',
        startDate: '',
        endDate: '',
        location: '',
        courseFee: '',
        instructorId:'',
        executorId:''
      },
      changeForm: {
        courseId:'',
        name: '',
        companyName: '',
        description: '',
        startDate: '',
        endDate: '',
        location: '',
        courseFee: '',
        instructorId:'',
        executorId:''
      },
    }
  },
  created () {
    //接收参数
      const name = this.$route.query.name;
      //console.log(name);
    this.getUserList(name)
  },
  methods: {
    //跳转并传参
    searchInstructor(){
      this.$router.push({name:'ExecutorChangeInstructor',query:{name:'ExecutorInstructorCourse'}});
    },
    //打开并展示审批通过的内容
    openApprovalTraining(){
      axios.get(`url`).then(
          response => {
            //console.log("数据库"+response.data.data.data);
             this.approvalList = response.data.data;
            //console.log("tableData"+this.tableData);
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
      );
      this.approvalDialogVisible=true;
    },
    openAddCourse(row){
      this.addDialogVisible =true;

    },
    //打开修改框的时候把原来的数据填上
    openChangeCourse(row){
      this.changeForm.courseId=row.courseId;
      this.changeForm.name=row.name;
      this.changeForm.companyName=row.companyName;
      this.changeForm.description=row.description;
      this.changeForm.startDate=row.startDate;
      this.changeForm.endDate=row.endDate;
      this.changeForm.location=row.location;
      this.changeForm.courseFee=row.courseFee;
      this.changeForm.instructorId=row.instructorId;
      this.changeForm.executorId=row.executorId;
      this.changeDialogVisible = true;
    },
    //修改课程
    changeCourse(){
      console.log("changeForm"+this.changeForm.name)
      axios
          .put("/executor/course/update", this.changeForm)
          .then((response) => {
            this.$message.success("修改成功");
            // 添加成功后刷新数据
            this.getUserList(null);
            // 关闭添加对话框
            this.changeDialogVisible = false;
          })
          .catch((error) => {
            console.error("修改失败", error);
            this.$message.error("修改失败");
          });
      // 关闭编辑对话框
      this.changeDialogVisible = false;
    },
    //新建课程,关闭对话框
    addCourse(){
      console.log("addForm.startDate"+this.addForm.startDate);
      this.addForm.startDate = this.addForm.startDate.format('YYYY-MM-DD');
      this.addForm.endDate = this.addForm.endDate.format('YYYY-MM-DD');
      axios
          .post("/executor/course/add", this.addForm)
          .then((response) => {
            this.$message.success("添加成功");
            // 添加成功后刷新数据
            this.getUserList(null);
            // 关闭添加对话框
            this.addDialogVisible = false;
          })
          .catch((error) => {
            console.error("添加失败", error);
            this.$message.error("添加失败");
          });
      // 关闭编辑对话框
      this.editDialogVisible = false;
    },
    getUserList (name) {
      console.log("getUserList"+name);
      axios.get(`/executor/course/list/${name}/150/1`).then(
          response => {
            //console.log("数据库"+response.data.data.data);
            this.courserList = response.data.data.data;
            //console.log("tableData"+this.tableData);
          },
          response => {
            console.log("error");
            alert("请求失败");
          }
      );
    },
    //删除课程信息
    removeData(row) {
      // 弹出确认对话框，确认后执行删除操作
      console.log("row"+row.courseId);
      ElMessageBox.confirm('确定删除['+row.name+']的信息吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 发送删除请求
        axios.delete(`/executor/course/delete/${row.courseId}`)
            .then(response => {
              this.$message.success('删除成功');
              // 删除成功后刷新数据
              this.getUserList(null);
            })
            .catch(error => {
              console.error("删除失败", error);
              this.$message.error('删除失败');
            });
      }).catch(() => {
        // 用户取消删除操作
      });
    },
  }

}
</script>

<style scoped>
.table-with-margin {
  margin-top: 20px; /* 调整表格与上方内容的间距，根据实际需要设置 */
}
</style>
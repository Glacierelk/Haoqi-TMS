<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <!-- 搜索与添加区域 -->
<!--          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getUserList">-->
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getUserList">
            <template #append>
              <el-button @click="getUserList">
                搜索
              </el-button>
            </template>

          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加用户</el-button>
        </el-col>
      </el-row>
      <!-- 用户列表区域  -->
      <el-table :data="userlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="姓名" prop="name"></el-table-column>
        <el-table-column label="联系电话" prop="contactInfo"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="职称" prop="title"></el-table-column>
        <el-table-column label="擅长的领域" prop="expertiseArea"></el-table-column>
        <el-table-column label="操作" width="180px">
          <template v-slot="scope">
            <!-- 修改按钮 -->
            <el-button type="primary" v-model="scope.row.Id" size="mini">修改</el-button>
            <!-- 删除按钮 -->
            <el-button type="danger" size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 页面区域 -->
      <el-pagination :current-page="queryInfo.pagenum" :page-sizes="[1, 2, 5, 10]"
                     :page-size="queryInfo.pagesize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange"
      >
      </el-pagination>
      <!-- 添加用户的对话框 -->
      <el-dialog v-model="addDialogVisible" title="添加用户" width="50%" :before-close="handleClose" >
        <!-- 内容主体区域 -->
        <el-form ref="addFormRef" :model="addForm"
                 :rules="addFormRules"
                 label-width="70px">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="contactInfo">
            <el-input v-model="addForm.password"></el-input>
          </el-form-item>
          <el-form-item label="Email" prop="email">
            <el-input v-model="addForm.email"></el-input>
          </el-form-item>
          <el-form-item label="职称" prop="title">
            <el-input v-model="addForm.mobile"></el-input>
          </el-form-item>
          <el-form-item label="擅长的领域" prop="expertiseArea">
            <el-input v-model="addForm.mobile"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部区域 -->
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addDialogVisible = false"
        >确定</el-button>
      </span>
        </template>
      </el-dialog>
    </el-card>
  </div>
</template>

<script >
export default {
  data () {
    return {
      // 获取用户列表的参数对象
      queryInfo: {
        query: '', // 查询参数
        pagenum: 1, // 当前页码
        pagesize: 2 // 每页显示条数
      },
      // 用户列表
      userlist: [
        {
        name: 'name',
        contactInfo: 'contactInfo',
        email: 'email',
        title: 'title',
        expertiseArea: 'expertiseArea',
        },
        {
          name: 'name',
          contactInfo: 'contactInfo',
          email: 'email',
          title: 'title',
          expertiseArea: 'expertiseArea',
        },
        {
          name: 'name',
          contactInfo: 'contactInfo',
          email: 'email',
          title: 'title',
          expertiseArea: 'expertiseArea',
        }
      ],
      // 总数据条数
      total: 0,
      // 控制添加用对话框的显示和隐藏，默认false,表示隐藏对话框
      addDialogVisible: false,
      // 添加用户的表单数据对象
      addForm: {
        username: '',
        password: '',
        email: '',
        mobile: ''
      },
      // 添加表单的验证规则对象
      // addFormRules: {
      //   username: [
      //     {
      //       required: true,
      //       message: '请输入用户名',
      //       trigger: 'blur'
      //     },
      //     {
      //       min: 3,
      //       max: 10,
      //       message: '用户名的长度在 3 - 10个字符之间',
      //       trigger: 'blur'
      //     }
      //   ],
      //   password: [
      //     {
      //       required: true,
      //       message: '请输入密码',
      //       trigger: 'blur'
      //     },
      //     {
      //       min: 6,
      //       max: 15,
      //       message: '密码的长度在 6 - 15个字符之间',
      //       trigger: 'blur'
      //     }
      //   ],
      //   email: [
      //     {
      //       required: true,
      //       message: '请输入邮箱',
      //       trigger: 'blur'
      //     }
      //   ],
      //   mobile: [
      //     {
      //       required: true,
      //       message: '请输入手机号',
      //       trigger: 'blur'
      //     },
      //     {
      //       min: 11,
      //       max: 11,
      //       message: '手机号长度在11个字符',
      //       trigger: 'blur'
      //     }
      //   ]
      // }
    }
  },
  created () {
    this.getUserList()
  },
  methods: {
    async getUserList () {
      const { data: res } = await this.$http.get('users', {
        params: this.queryInfo
      })
      if (res.meta.status !== 200) return this.$message.error('获取用户列表失败')
      this.userlist = res.data.users
      this.total = res.data.total
      console.log(res)
    },
    // 监听 page size 改变的事件
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getUserList()
    },
    // 监听 页码值 改变的事件
    handleCurrentChange (newPage) {
      this.queryInfo.pagenum = newPage
      this.getUserList()
    },
    // 监听 switch 开头状态的改变
    async userStateChanged (userinfo) {
      const { data: res } = await this.$http.put(`users/${userinfo.id}/state/${userinfo.mg_state}`)
      if (res.meta.status !== 200) {
        userinfo.mg_state = !userinfo.mg_state
        return this.$message.error('更新用户状态失败')
      }
      this.$message.success('更新用户状态成功')
    }
  }

}
</script>

<style lang="less" scoped>

</style>
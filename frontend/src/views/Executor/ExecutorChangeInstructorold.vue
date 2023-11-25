<template>
  <div>
    <el-table :data="instructorData" style="width: 100%">
      <el-table-column fixed prop="name" label="姓名" width="150" />
      <el-table-column prop="contactInfo" label="联系电话" width="120" />
      <el-table-column prop="email" label="Email" width="120" />
      <el-table-column prop="title" label="职称" width="120" />
      <el-table-column prop="expertiseArea" label="擅长的领域" width="600" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{ row }">
          <div class="table-actions">
            <el-button type="primary" size="small" @click="showEditDialog(row)">
              <i class="el-icon-view"></i> 修改
            </el-button>
            <el-button type="success" size="small" @click="deleteClick(row)">
              <i class="el-icon-edit"></i> 删除
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <el-button @click="editAddUser()" type="primary" size="mini" round class="add-user-button">
      <i class="el-icon-delete"></i> 新增讲师
    </el-button>


    <el-dialog v-model="editDialogVisible" title="教师信息" width="40%" center>
      <el-form :model="instructorForm" label-width="120px">
        <el-form-item label="姓名">
          <el-input v-model="instructorForm.name" />
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="instructorForm.contactInfo" />
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="instructorForm.email" />
        </el-form-item>
        <el-form-item label="头衔">
          <el-input v-model="instructorForm.title" />
        </el-form-item>
        <el-form-item label="擅长的领域">
          <el-input v-model="instructorForm.expertiseArea" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="cancelEditDialog">取消</el-button>
          <el-button type="primary" @click="onSubmit">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { reactive } from 'vue';

const instructorData = [
  {
    name: 'name',
    contactInfo: 'contactInfo',
    email: 'email',
    title: 'title',
    expertiseArea: 'expertiseArea',
  },
];

const instructorForm = reactive({
  name: 'name',
  contactInfo: 'contactInfo',
  email: 'email',
  title: 'title',
  expertiseArea: 'expertiseArea',
});

const editDialogVisible = ref(false);

const showEditDialog = (rowData) => {
  // Populate the form with the selected row's data
  Object.assign(instructorForm, rowData);
  editDialogVisible.value = true;
};

const cancelEditDialog = () => {
  editDialogVisible.value = false;
};

const onSubmit = () => {
  console.log('submit!');
};
</script>

<style scoped>
.table-actions {
  display: flex;
  gap: 8px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.add-user-button {
  position: fixed;
  margin-top: 20px; /* 设置与上个组件的间距 */
  right: 20px; /* 距离右侧的距离 */
}

</style>

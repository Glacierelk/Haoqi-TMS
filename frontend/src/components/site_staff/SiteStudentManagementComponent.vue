<template>
  <div>
    <a-form
        ref="formRef"
        :model="formState"
        class="ant-advanced-search-form"
        name="advanced_search"
        @finish="search"
    >
      <a-row :gutter="24">
        <template v-for="i in 4" :key="i">
            <a-col :span="6">
              <a-form-item
                  :label=columns[i-1]
                  :name=names[i-1]
                  :rules="[{ required: true, message: '请输入课程 ID' }]"
                  v-if="i === 1"
              >
                <a-input v-model:value="formState[names[i-1]]"/>
              </a-form-item>

              <a-form-item
                  :label=columns[i-1]
                  :name=names[i-1]
                  v-if="i > 1"
              >
                <a-input v-model:value="formState[names[i-1]]" />
              </a-form-item>
            </a-col>
        </template>
      </a-row>

      <a-row>
        <a-col :span="24" style="text-align: right">
          <a-button type="primary" :icon="h(SearchOutlined)" html-type="submit">查询</a-button>
          <a-button style="margin: 0 8px" @click="reset">清空输入</a-button>
        </a-col>
      </a-row>
    </a-form>

    <div class="search-result-list" v-if="showTable">
      <StudentListComponent :query="queryForm"/>
    </div>
  </div>
</template>

<script setup>

import {h, reactive, ref} from 'vue';
import { SearchOutlined } from '@ant-design/icons-vue';
import StudentListComponent from "@/components/site_staff/list/StudentListComponent.vue";

const formRef = ref();
const formState = reactive({});
const columns = reactive(["课程 ID", "学员姓名", "联系电话", "公司名称"]);
const names = reactive(["courseId", "name", "contactInfo", "companyName"]);
const showTable = ref(false);
let queryForm = ref({});

const search = values => {
  if (values.name === undefined) values.name = 'null';
  if (values.contactInfo === undefined) values.contactInfo = 'null';
  if (values.companyName === undefined) values.companyName = 'null';

  values.courseId = values.courseId.trim();
  values.name = values.name.trim();
  values.contactInfo = values.contactInfo.trim();
  values.companyName = values.companyName.trim();

  if (values.name === "") values.name = 'null';
  if (values.contactInfo === "") values.contactInfo = 'null';
  if (values.companyName === "") values.companyName = 'null';

  queryForm.value = values;
  showTable.value = true;
};

const reset = () => {
  formRef.value.resetFields();
  showTable.value = false;
};

</script>

<style scoped>

#components-form-demo-advanced-search .ant-form {
  max-width: none;
}

#components-form-demo-advanced-search .search-result-list {
  margin-top: 16px;
  border: 1px dashed #e9e9e9;
  border-radius: 2px;
  background-color: #fafafa;
  min-height: 200px;
  text-align: center;
  padding-top: 80px;
}

[data-theme='dark'] .ant-advanced-search-form {
  background: rgba(255, 255, 255, 0.04);
  border: 1px solid #434343;
  padding: 24px;
  border-radius: 2px;
}

[data-theme='dark'] #components-form-demo-advanced-search .search-result-list {
  border: 1px dashed #434343;
  background: rgba(255, 255, 255, 0.04);
}

.search-result-list {
  margin-top: 16px;
}

</style>
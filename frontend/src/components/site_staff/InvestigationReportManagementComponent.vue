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
        <template v-for="i in 1" :key="i">
          <a-col :span="24">
            <a-form-item
                :label=columns[i-1]
                :name=names[i-1]
                :rules="[{ required: true, message: '请输入课程 ID' }]"
                v-if="i === 1"
            >
              <a-input v-model:value="formState[names[i-1]]"/>
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
      <EvaluationListComponent :courseId="courseID" />
    </div>
  </div>
</template>

<script setup>

import axios from 'axios';
import {h, reactive, ref} from 'vue';
import { ElMessage } from 'element-plus';
import { SearchOutlined } from '@ant-design/icons-vue';
import EvaluationListComponent from "@/components/site_staff/list/EvaluationListComponent.vue";

const formRef = ref();
const formState = reactive({});
const columns = reactive(["课程 ID"]);
const names = reactive(["courseId"]);
const showTable = ref(false);
let tableData = reactive([]);
const courseID = ref("");

const search = values => {
  courseID.value = values.courseId;
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
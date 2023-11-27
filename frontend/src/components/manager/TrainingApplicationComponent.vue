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
                v-if="i < 3"
            >
              <a-input v-model:value="formState[names[i-1]]"/>
            </a-form-item>

            <a-form-item
                :label=columns[i-1]
                :name=names[i-1]
                v-else
            >
              <a-date-picker v-model:value="formState[names[i-1]]" style="width: 100%" />
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

    <div class="search-result-list">
      <CourseListComponent :queryForm="queryForm"/>
    </div>
  </div>
</template>

<script setup>

import {h, reactive, ref} from 'vue';
import { SearchOutlined } from '@ant-design/icons-vue';
import CourseListComponent from "@/components/home/CourseListComponent.vue";

const formRef = ref();
const formState = reactive({});
const columns = reactive(["公司名称", "最早提交时间", "审批状态"]);
const names = reactive(["companyName", "earliestSubmitTime", "status"]);
let queryForm = ref({});

const search = values => {
  if (values.courseName === undefined) values.courseName = 'null';
  if (values.instructorName === undefined) values.instructorName = 'null';
  if (values.companyName === undefined) values.companyName = 'null';

  if (values.earliestStartTime === undefined) values.earliestStartTime = '1970-01-01';
  else values.earliestStartTime = values.earliestStartTime.format('YYYY-MM-DD');

  values.courseName = values.courseName.trim();
  values.instructorName = values.instructorName.trim();
  values.companyName = values.companyName.trim();

  if (values.courseName === '') values.courseName = 'null';
  if (values.instructorName === '') values.instructorName = 'null';
  if (values.companyName === '') values.companyName = 'null';

  queryForm.value = values;
};

const reset = () => {
  formRef.value.resetFields();
  search({
    courseName: 'null',
    instructorName: 'null',
    companyName: 'null',
    earliestStartTime: undefined
  });
};

search({
  courseName: 'null',
  instructorName: 'null',
  companyName: 'null',
  earliestStartTime: undefined
});

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
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
                v-if="i === 1"
            >
              <a-input v-model:value="formState[names[i-1]]"/>
            </a-form-item>

            <a-form-item
                :label=columns[i-1]
                :name=names[i-1]
                v-if="i === 3"
            >
              <a-select
                  v-model:value="formState[names[i-1]]"
                  style="width: 100%"
                  :options="options"

              />
            </a-form-item>

            <a-form-item
                :label=columns[i-1]
                :name=names[i-1]
                v-if="i === 2"
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
      <ApplicationListComponent :queryForm="queryForm" />
    </div>
  </div>
</template>

<script setup>

import {h, reactive, ref} from 'vue';
import { SearchOutlined } from '@ant-design/icons-vue';
import ApplicationListComponent from "@/components/manager/list/ApplicationListComponent.vue";

const formRef = ref();
const formState = reactive({});
const columns = reactive(["公司名称", "最早提交时间", "审批状态"]);
const names = reactive(["companyName", "earliestSubmitTime", "status"]);
let queryForm = ref({});
const options = reactive([
  {value: '-1', label: '不限'},
  {value: '0', label: '待审批'},
  {value: '1', label: '接受'},
  {value: '2', label: '拒绝'}
]);

const search = values => {
  if (values.companyName === undefined) values.companyName = 'null';
  if (values.earliestSubmitTime === undefined) values.earliestSubmitTime = '1970-01-01';
  else values.earliestSubmitTime = values.earliestSubmitTime.format('YYYY-MM-DD');
  if (values.status === undefined) values.status = '-1';

  values.companyName = values.companyName.trim();

  if (values.companyName === '') values.companyName = 'null';

  console.log(values);

  queryForm.value = values;
};

const reset = () => {
  formRef.value.resetFields();
  search({
    companyName: 'null',
    earliestSubmitTime: undefined,
    status: '-1'
  });
};

search({
  companyName: 'null',
  earliestSubmitTime: undefined,
  status: '-1'
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
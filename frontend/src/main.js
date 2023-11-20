import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/main.css'
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import 'font-awesome/css/font-awesome.min.css';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.use(Antd)

app.mount('#app')

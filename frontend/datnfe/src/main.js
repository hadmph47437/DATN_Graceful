import { createApp } from 'vue';
import App from './App.vue';
import router from './router/router.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css'; 
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'

const app = createApp(App);

app.use(router)
app.config.globalProperties.$toast = toast
app.mount('#app')

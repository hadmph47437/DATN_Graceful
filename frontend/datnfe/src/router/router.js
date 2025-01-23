import { createRouter, createWebHistory } from 'vue-router';
import Listnhanvien from '../components/nhanvien/listnhanvien.vue';
import Addnhanvien from '../components/nhanvien/addnhanvien.vue';
import Updatenhanvien from '../components/nhanvien/updatenhanvien.vue';
// import HocSinh from '../components/hoc-sinh/HocSinh.vue';

const routes = [
    // { path: '/', component: HocSinh }
      { path: '/', 
        component: Listnhanvien },
      { path: '/add',
        component:Addnhanvien},
        { path: '/update/:id',
        component:Updatenhanvien},

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
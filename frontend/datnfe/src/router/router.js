
// datnfe/src/router/router.js
import { createRouter, createWebHistory } from 'vue-router';
import HoaDon from '../components/hoadon/HoaDonList.vue';
import login from '../components/login/login.vue';
import BanHang from '../components/banhang/BanHang.vue';


const routes = [
    { path: '/', component: login },
    { path: '/ban-hang', component: BanHang },
    {path : '/hoa-don/hien-thi', component: HoaDon}

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
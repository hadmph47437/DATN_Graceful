import { createRouter, createWebHistory } from 'vue-router';
// import HocSinh from '../components/hoc-sinh/HocSinh.vue';

const routes = [
    // { path: '/', component: HocSinh }

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
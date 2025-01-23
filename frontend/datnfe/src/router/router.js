import { createRouter, createWebHistory } from "vue-router";
import ListKhachHang from "../components/khachhang/ListKhachHang.vue";

const routes = [
  { path: "/", redirect: "/list" },
  { path: "/list", component: ListKhachHang },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

import { createRouter, createWebHistory } from "vue-router";
import ListKhachHang from "../components/khachhang/ListKhachHang.vue";
import EditKhachHang from "../components/khachhang/EditKhachHang.vue";

const routes = [
  { path: "/", redirect: "/list" },
  { path: "/list", component: ListKhachHang },
  { path: "/update/:id", component: EditKhachHang },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

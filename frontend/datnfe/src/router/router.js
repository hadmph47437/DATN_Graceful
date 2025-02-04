import { createRouter, createWebHistory } from "vue-router";
import ListKhachHang from "../components/khachhang/ListKhachHang.vue";
import EditKhachHang from "../components/khachhang/EditKhachHang.vue";
import ViewKhachHang from "../components/khachhang/ViewKhachHang.vue";
import AddKhachHang from "../components/khachhang/AddKhachHang.vue";
import ThongKe from "../components/thongke/ThongKe.vue";



const routes = [
  { path: "/", redirect: "/list" },
  { path: "/list", component: ListKhachHang },
  { path: "/add", component: AddKhachHang },
  { path: "/update/:id", component: EditKhachHang },
  { path: "/view/:id", component: ViewKhachHang},
  { path: "/thong-ke", component: ThongKe}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

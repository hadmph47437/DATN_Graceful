import { createRouter, createWebHistory } from "vue-router";
import ListKhachHang from "../components/khachhang/ListKhachHang.vue";
import EditKhachHang from "../components/khachhang/EditKhachHang.vue";
import ViewKhachHang from "../components/khachhang/ViewKhachHang.vue";
import AddKhachHang from "../components/khachhang/AddKhachHang.vue";
import ThongKe from "../components/thongke/ThongKe.vue";



const routes = [
  { path: "/khach-hang", redirect: "/khach-hang/list" },
  { path: "/khach-hang/list", component: ListKhachHang },
  { path: "/khach-hang/add", component: AddKhachHang },
  { path: "/khach-hang/update/:id", component: EditKhachHang },
  { path: "/khach-hang/view/:id", component: ViewKhachHang},
  { path: "/thong-ke", component: ThongKe}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

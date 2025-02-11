import { createRouter, createWebHistory } from "vue-router";
import ListKhachHang from "../components/khachhang/ListKhachHang.vue";
import EditKhachHang from "../components/khachhang/EditKhachHang.vue";
import ViewKhachHang from "../components/khachhang/ViewKhachHang.vue";
import AddKhachHang from "../components/khachhang/AddKhachHang.vue";
import ThongKe from "../components/thongke/ThongKe.vue";

const routes = [
  { path: "/khach-hang", redirect: "/khach-hang/list" },
  {
    path: "/khach-hang/list",
    component: () => import("../components/khachhang/ListKhachHang.vue"),
    props: true,
    meta: { isAdmin: true },
  },
  {
    path: "/khach-hang/add",
    component: () => import("../components/khachhang/AddKhachHang.vue"),
    props: true,
    meta: { isAdmin: true },
  },
  {
    path: "/khach-hang/update/:id",
    component: () => import("../components/khachhang/EditKhachHang.vue"),
    props: true,
    meta: { isAdmin: true },
  },
  {
    path: "/khach-hang/view/:id",
    component: () => import("../components/khachhang/ViewKhachHang.vue"),
    props: true,
    meta: { isAdmin: true },
  },
  {
    path: "/thong-ke",
    component: () => import("../components/thongke/ThongKe.vue"),
    props: true,
    meta: { isAdmin: true },
  },

  //   {
  //     path: '/san-pham/list/all/:status?',
  //     component: () => import('../components/san_pham/Tabs.vue'),
  //     props: true,
  //     meta: { isAdmin: true }
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

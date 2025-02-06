<template>
  <div class="container mt-4">
    <h2>Thêm mới khách hàng</h2>
    <form @submit.prevent="addKhachHang" class="row g-3">
      <div class="col-md-6">
        <label class="form-label">Họ và tên</label>
        <input
          v-model="khachHang.hoTen"
          type="text"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6">
        <label class="form-label">Giới tính</label>
        <select v-model="khachHang.gioiTinh" class="form-select" required>
          <option :value="true">Nam</option>
          <option :value="false">Nữ</option>
        </select>
      </div>

      <div class="col-md-6">
        <label class="form-label">Ngày sinh</label>
        <input
          v-model="khachHang.ngaySinh"
          type="date"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6">
        <label class="form-label">Email</label>
        <input
          v-model="khachHang.email"
          type="email"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6">
        <label class="form-label">Số điện thoại</label>
        <input
          v-model="khachHang.soDienThoai"
          type="tel"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6">
        <label class="form-label">Tên đăng nhập</label>
        <input
          v-model="khachHang.tenDangNhap"
          type="text"
          class="form-control"
          required
        />
      </div>

      <div class="col-12">
        <h4>Địa chỉ</h4>
      </div>

      <div class="col-md-6">
        <label class="form-label">Đường</label>
        <input
          v-model="diaChi.duong"
          type="text"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6">
        <label class="form-label">Quận</label>
        <input
          v-model="diaChi.quan"
          type="text"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6">
        <label class="form-label">Thành phố</label>
        <input
          v-model="diaChi.thanhPho"
          type="text"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6">
        <label class="form-label">Tỉnh</label>
        <input
          v-model="diaChi.tinh"
          type="text"
          class="form-control"
          required
        />
      </div>

      <div class="col-12">
        <div class="form-check">
          <input
            v-model="diaChi.macDinh"
            type="checkbox"
            class="form-check-input"
            id="macDinh"
          />
          <label class="form-check-label" for="macDinh"
            >Đặt làm địa chỉ mặc định</label
          >
        </div>
      </div>

      <div class="col-12">
        <button type="submit" class="btn btn-primary me-2">Lưu</button>
        <button
          type="button"
          @click="$router.push('/khach-hang/list')"
          class="btn btn-secondary"
        >
          Hủy
        </button>
      </div>
    </form>
  </div>
</template>
<script>
import KhachHangService from "../../services/KhachHangService";
import { toast } from "vue3-toastify";
export default {
  data() {
    return {
      khachHang: {
        hoTen: "",
        gioiTinh: true,
        ngaySinh: "",
        email: "",
        soDienThoai: "",
        tenDangNhap: "",
        ngayTao: new Date().toISOString().split("T")[0],
        diaChis: [],
      },
      diaChi: {
        duong: "",
        quan: "",
        thanhPho: "",
        tinh: "",
        macDinh: false,
      },
    };
  },
  methods: {
    addKhachHang() {
      this.khachHang.diaChis = [this.diaChi];

      KhachHangService.addKhachHang(this.khachHang)
        .then((response) => {
          this.$toast.success("Thêm khách hàng thành công!", {
            position: "top-right",
            autoClose: 2000,
            closeOnClick: true,
            pauseOnHover: true,
          });
          setTimeout(() => {
            this.$router.push("/khach-hang/list");
          }, 2000);
        })
        .catch((error) => {
          console.error("Error adding customer:", error);
          this.$toast.error("Có lỗi xảy ra khi thêm khách hàng!", {
            position: "top-right",
            autoClose: 3000,
            closeOnClick: true,
            pauseOnHover: true,
          });
        });
    },
  },
};
</script>

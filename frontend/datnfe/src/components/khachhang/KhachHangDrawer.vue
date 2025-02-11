<template>
  <div class="customer-drawer" :class="{ 'drawer-open': isOpen }">
    <div class="drawer-overlay" @click="closeDrawer" v-if="isOpen"></div>
    <div class="drawer-content">
      <button class="drawer-close" @click="closeDrawer">
        <i class="bi bi-x-lg"></i>
      </button>
      <div v-if="khachHang" class="customer-details">
        <h2 class="mb-3">Chi tiết khách hàng</h2>
        <div class="card">
          <div class="card-body">
            <div class="row mb-3">
              <div class="col-md-6">
                <label><strong>Mã khách hàng:</strong></label>
                <p>{{ khachHang.maKhachHang }}</p>
              </div>
              <div class="col-md-6">
                <label><strong>Họ và tên:</strong></label>
                <p>{{ khachHang.hoTen }}</p>
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-md-6">
                <label><strong>Giới tính:</strong></label>
                <p>{{ khachHang.gioiTinh ? "Nam" : "Nữ" }}</p>
              </div>
              <div class="col-md-6">
                <label><strong>Ngày sinh:</strong></label>
                <p>{{ khachHang.ngaySinh }}</p>
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-md-6">
                <label><strong>Email:</strong></label>
                <p>{{ khachHang.email }}</p>
              </div>
              <div class="col-md-6">
                <label><strong>Số điện thoại:</strong></label>
                <p>{{ khachHang.soDienThoai }}</p>
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-md-6">
                <label><strong>Tên đăng nhập:</strong></label>
                <p>{{ khachHang.tenDangNhap }}</p>
              </div>
              <div class="col-md-6">
                <label><strong>Địa chỉ:</strong></label>
                <p>
                  {{
                    `${khachHang.diaChi_duong}, ${khachHang.diaChi_quan}, ${khachHang.diaChi_thanhPho}`
                  }}
                </p>
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-md-6">
                <label><strong>Ngày tạo:</strong></label>
                <p>{{ khachHang.ngayTao }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import KhachHangService from "../../services/KhachHangService";
export default {
  props: {
    isOpen: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      khachHang: null,
    };
  },
  methods: {
    fetchKhachHang(id) {
      KhachHangService.getKhachHangById(id)
        .then((response) => {
          const khachHang = response.data;

          // Xử lý địa chỉ mặc định
          const diaChiMacDinh =
            khachHang.diaChis.find((d) => d.macDinh) || khachHang.diaChis[0];

          khachHang.diaChi_duong = diaChiMacDinh?.duong || "Không có thông tin";
          khachHang.diaChi_quan = diaChiMacDinh?.quan || "Không có thông tin";
          khachHang.diaChi_thanhPho =
            diaChiMacDinh?.thanhPho || "Không có thông tin";

          this.khachHang = khachHang;
        })
        .catch((error) => {
          console.error(error);
          alert("Không thể lấy dữ liệu khách hàng!");
        });
    },
    closeDrawer() {
      this.$emit("close");
    },
  },
};
</script>

<style scoped>
.customer-drawer {
  position: fixed;
  top: 0;
  right: -500px;
  width: 500px;
  height: 100vh;
  background: white;
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.1);
  transition: right 0.3s ease;
  z-index: 1000;
  overflow-y: auto;
  padding: 20px;
}

.drawer-open {
  right: 0;
}

.drawer-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(248, 248, 248, 0.5);
  z-index: 999;
}

.drawer-close {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
}

.customer-details {
  margin-top: 40px;
}
</style>

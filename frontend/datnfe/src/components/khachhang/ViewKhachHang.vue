<template>
  <div class="container mt-4">
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
    <div class="mt-3">
      <button class="btn btn-secondary" @click="$router.push('/list')">
        Quay lại
      </button>
    </div>
  </div>
</template>

<script>
import KhachHangService from "../../services/KhachHangService";

export default {
  data() {
    return {
      khachHang: {},
    };
  },
  created() {
    this.fetchKhachHang();
  },
  methods: {
    fetchKhachHang() {
      const id = this.$route.params.id;
      KhachHangService.getKhachHangById(id)
        .then((response) => {
          this.khachHang = response.data;

          // Tìm địa chỉ mặc định (nếu có), hoặc lấy địa chỉ đầu tiên
          const diaChiMacDinh =
            this.khachHang.diaChis.find((d) => d.macDinh) ||
            this.khachHang.diaChis[0];
          this.khachHang.diaChi_duong =
            diaChiMacDinh?.duong || "Không có thông tin";
          this.khachHang.diaChi_quan =
            diaChiMacDinh?.quan || "Không có thông tin";
          this.khachHang.diaChi_thanhPho =
            diaChiMacDinh?.thanhPho || "Không có thông tin";
        })
        .catch((error) => {
          console.error(error);
          alert("Không thể lấy dữ liệu khách hàng!");
        });
    },
  },
};
</script>

<style>
.container {
  max-width: 800px;
}
</style>

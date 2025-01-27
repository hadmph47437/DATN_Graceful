<template>
  <div class="container mt-4">
    <h2 class="">Chỉnh sửa thông tin khách hàng</h2>
    <form @submit.prevent="updateKhachHang">
      <div class="mb-3">
        <label class="form-label">Họ và tên</label>
        <input
          type="text"
          v-model="khachHang.hoTen"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Ngày sinh</label>
        <input
          type="date"
          v-model="khachHang.ngaySinh"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Email</label>
        <input
          type="email"
          v-model="khachHang.email"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Số điện thoại</label>
        <input
          type="tel"
          v-model="khachHang.soDienThoai"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Tên đăng nhập</label>
        <input
          type="text"
          v-model="khachHang.tenDangNhap"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Địa chỉ đường</label>
        <input
          type="text"
          v-model="khachHang.diaChi_duong"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Quận</label>
        <input
          type="text"
          v-model="khachHang.diaChi_quan"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Thành phố</label>
        <input
          type="text"
          v-model="khachHang.diaChi_thanhPho"
          class="form-control"
          required
        />
      </div>
      <div class="mb-3">
        <label class="form-label">Giới tính</label>
        <div class="form-check">
          <input
            class="form-check-input"
            type="radio"
            id="gioiTinhNam"
            :value="true"
            v-model="khachHang.gioiTinh"
          />
          <label class="form-check-label" for="gioiTinhNam">Nam</label>
        </div>
        <div class="form-check">
          <input
            class="form-check-input"
            type="radio"
            id="gioiTinhNu"
            :value="false"
            v-model="khachHang.gioiTinh"
          />
          <label class="form-check-label" for="gioiTinhNu">Nữ</label>
        </div>
      </div>
      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <button type="button" class="btn btn-secondary" @click="cancelEdit">
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
        id: null,
        maKhachHang: "",
        hoTen: "",
        ngaySinh: "",
        email: "",
        soDienThoai: "",
        tenDangNhap: "",
        diaChi_duong: "",
        diaChi_quan: "",
        diaChi_thanhPho: "",
        gioiTinh: null,
      },
    };
  },
  created() {
    // Lấy ID khách hàng từ route
    const id = this.$route.params.id;
    if (id) {
      this.getKhachHangById(id);
    }
  },
  methods: {
    getKhachHangById(id) {
        KhachHangService.getKhachHangById(id)
            .then((response) => {
                this.khachHang = response.data;
                
                // Nếu có địa chỉ, lấy địa chỉ mặc định hoặc địa chỉ đầu tiên
                if (this.khachHang.diaChis && this.khachHang.diaChis.length > 0) {
                    const diaChi = this.khachHang.diaChis.find(dc => dc.macDinh) || this.khachHang.diaChis[0];
                    
                    this.khachHang.diaChi_duong = diaChi.duong;
                    this.khachHang.diaChi_quan = diaChi.quan;
                    this.khachHang.diaChi_thanhPho = diaChi.thanhPho;
                }
            })
            .catch((error) => {
                console.error("Lỗi khi lấy thông tin khách hàng:", error);
                this.$router.push("/list");
            });
    },
    updateKhachHang() {
        KhachHangService.updateKhachHang(this.khachHang.id, this.khachHang)
            .then(() => {
                this.$toast.success('Cập nhật khách hàng thành công!', {
                    position: 'top-right',
                    autoClose: false,
                    closeOnClick: true,
                    pauseOnHover: true
                });
                this.$router.push('/list');
            })
            .catch(error => {
                this.$toast.error('Cập nhật khách hàng thất bại!',{
                    autoClose: false,
                    closeOnClick: true,
                    pauseOnHover: true
                });
                
            });
    },
    cancelEdit() {
      this.$router.push("/list");
    },
  },
};
</script>

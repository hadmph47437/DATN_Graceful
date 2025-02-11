<template>
  <div class="main-content">
    <div class="form-container">
      <div class="form-header">
        <h2>Thêm mới khách hàng</h2>
      </div>

      <form @submit.prevent="addKhachHang" class="custom-form">
        <div class="form-section">
          <h4 class="section-title">Thông tin cá nhân</h4>
          <div class="form-grid">
            <div class="form-group">
              <label class="form-label">Họ và tên</label>
              <input
                v-model="khachHang.hoTen"
                type="text"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Giới tính</label>
              <select v-model="khachHang.gioiTinh" class="form-select" required>
                <option :value="true">Nam</option>
                <option :value="false">Nữ</option>
              </select>
            </div>

            <div class="form-group">
              <label class="form-label">Ngày sinh</label>
              <input
                v-model="khachHang.ngaySinh"
                type="date"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Email</label>
              <input
                v-model="khachHang.email"
                type="email"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Số điện thoại</label>
              <input
                v-model="khachHang.soDienThoai"
                type="tel"
                class="form-input"
                required
              />
            </div>
          </div>
        </div>

        <div class="form-section">
          <h4 class="section-title">Thông tin địa chỉ</h4>
          <div class="form-grid">
            <div class="form-group">
              <label class="form-label">Đường</label>
              <input
                v-model="diaChi.duong"
                type="text"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Quận</label>
              <input
                v-model="diaChi.quan"
                type="text"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Thành phố</label>
              <input
                v-model="diaChi.thanhPho"
                type="text"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Tỉnh</label>
              <input
                v-model="diaChi.tinh"
                type="text"
                class="form-input"
                required
              />
            </div>
          </div>

          <div class="checkbox-group">
            <label class="checkbox-container">
              <input
                v-model="diaChi.macDinh"
                type="checkbox"
                class="custom-checkbox"
              />
              <span class="checkmark"></span>
              Đặt làm địa chỉ mặc định
            </label>
          </div>
        </div>

        <div class="form-actions">
          <button type="submit" class="btn-save">
            <i class="bi bi-check-circle me-2"></i>Lưu
          </button>
          <button
            type="button"
            @click="$router.push('/khach-hang/list')"
            class="btn-cancel"
          >
            <i class="bi bi-x-circle me-2"></i>Hủy
          </button>
        </div>
      </form>
    </div>
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

<style scoped>
.main-content {
  padding: 20px;
  background-color: #f8f9fa;
  min-height: 100vh;
}

.form-container {
  background: white;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  padding: 24px;
  max-width: 1200px;
  margin: 0 auto;
}

.form-header {
  margin-bottom: 24px;
  padding-bottom: 16px;
  border-bottom: 1px solid #e9ecef;
}

.form-header h2 {
  color: #2c3e50;
  font-size: 24px;
  font-weight: 600;
  margin: 0;
}

.custom-form {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.form-section {
  background-color: #f8f9fa;
  border-radius: 8px;
  padding: 20px;
}

.section-title {
  color: #495057;
  font-size: 18px;
  margin-bottom: 20px;
  font-weight: 600;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-label {
  color: #495057;
  font-weight: 500;
  font-size: 14px;
}

.form-input,
.form-select {
  padding: 10px 12px;
  border: 1px solid #dee2e6;
  border-radius: 6px;
  font-size: 14px;
  transition: all 0.3s ease;
}

.form-input:focus,
.form-select:focus {
  outline: none;
  border-color: #0d6efd;
  box-shadow: 0 0 0 3px rgba(13, 110, 253, 0.25);
}

.checkbox-group {
  margin-top: 16px;
}

.checkbox-container {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  font-size: 14px;
  color: #495057;
}

.custom-checkbox {
  width: 18px;
  height: 18px;
  margin: 0;
}

.form-actions {
  display: flex;
  gap: 12px;
  padding-top: 20px;
  border-top: 1px solid #e9ecef;
}

.btn-save,
.btn-cancel {
  padding: 10px 24px;
  border: none;
  border-radius: 6px;
  font-weight: 500;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: all 0.3s ease;
}

.btn-save {
  background-color: #0d6efd;
  color: white;
}

.btn-save:hover {
  background-color: #0b5ed7;
  transform: translateY(-1px);
}

.btn-cancel {
  background-color: #6c757d;
  color: white;
}

.btn-cancel:hover {
  background-color: #5c636a;
  transform: translateY(-1px);
}

@media (max-width: 768px) {
  .form-container {
    padding: 16px;
  }

  .form-grid {
    grid-template-columns: 1fr;
  }

  .form-actions {
    flex-direction: column;
  }

  .btn-save,
  .btn-cancel {
    width: 100%;
    justify-content: center;
  }
}
</style>

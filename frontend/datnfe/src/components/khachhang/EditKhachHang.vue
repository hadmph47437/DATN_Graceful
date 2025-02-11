<template>
  <div class="edit-container">
    <div class="edit-card">
      <h2 class="edit-title">Chỉnh sửa thông tin khách hàng</h2>
      
      <form @submit.prevent="updateKhachHang" class="edit-form">
        <div class="form-grid">
          <!-- Cột thông tin cá nhân -->
          <div class="form-column">
            <div class="form-group">
              <label class="form-label">Họ và tên</label>
              <input
                type="text"
                v-model="khachHang.hoTen"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Ngày sinh</label>
              <input
                type="date"
                v-model="khachHang.ngaySinh"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Email</label>
              <input
                type="email"
                v-model="khachHang.email"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Số điện thoại</label>
              <input
                type="tel"
                v-model="khachHang.soDienThoai"
                class="form-input"
                required
              />
            </div>
          </div>

          <!-- Cột thông tin địa chỉ -->
          <div class="form-column">
            <div class="form-group">
              <label class="form-label">Địa chỉ đường</label>
              <input
                type="text"
                v-model="khachHang.diaChi_duong"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Quận</label>
              <input
                type="text"
                v-model="khachHang.diaChi_quan"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Thành phố</label>
              <input
                type="text"
                v-model="khachHang.diaChi_thanhPho"
                class="form-input"
                required
              />
            </div>

            <div class="form-group">
              <label class="form-label">Giới tính</label>
              <div class="radio-group">
                <label class="radio-label">
                  <input
                    type="radio"
                    :value="true"
                    v-model="khachHang.gioiTinh"
                    class="radio-input"
                  />
                  <span>Nam</span>
                </label>
                <label class="radio-label">
                  <input
                    type="radio"
                    :value="false"
                    v-model="khachHang.gioiTinh"
                    class="radio-input"
                  />
                  <span>Nữ</span>
                </label>
              </div>
            </div>
          </div>
        </div>

        <div class="form-actions">
          <button type="button" @click="cancelEdit" class="btn btn-cancel">
            <i class="fas fa-times"></i> Hủy
          </button>
          <button type="submit" class="btn btn-update">
            <i class="fas fa-save"></i> Cập nhật
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
          if (this.khachHang.diaChis && this.khachHang.diaChis.length > 0) {
            const diaChi = this.khachHang.diaChis.find((dc) => dc.macDinh) || 
                          this.khachHang.diaChis[0];
            this.khachHang.diaChi_duong = diaChi.duong;
            this.khachHang.diaChi_quan = diaChi.quan;
            this.khachHang.diaChi_thanhPho = diaChi.thanhPho;
          }
        })
        .catch((error) => {
          console.error("Lỗi khi lấy thông tin khách hàng:", error);
          this.$router.push("/khach-hang/list");
        });
    },
    updateKhachHang() {
      KhachHangService.updateKhachHang(this.khachHang.id, this.khachHang)
        .then(() => {
          toast.success("Cập nhật khách hàng thành công!", {
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
          toast.error("Cập nhật khách hàng thất bại!", {
            autoClose: 3000,
            closeOnClick: true,
            pauseOnHover: true,
          });
        });
    },
    cancelEdit() {
      this.$router.push("/khach-hang/list");
    },
  },
};
</script>

<style scoped>
.edit-container {
  padding: 2rem;
}

.edit-card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 2rem;
  max-width: 1200px;
  margin: 0 auto;
}

.edit-title {
  color: #2c3e50;
  font-size: 1.5rem;
  font-weight: 600;
  margin-bottom: 2rem;
  padding-bottom: 1rem;
  border-bottom: 2px solid #edf2f7;
}

.form-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-label {
  display: block;
  font-weight: 500;
  color: #4a5568;
  margin-bottom: 0.5rem;
}

.form-input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #e2e8f0;
  border-radius: 6px;
  transition: all 0.3s ease;
}

.form-input:focus {
  outline: none;
  border-color: #4299e1;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.1);
}

.radio-group {
  display: flex;
  gap: 2rem;
  margin-top: 0.5rem;
}

.radio-label {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.radio-input {
  margin-right: 0.5rem;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
  margin-top: 2rem;
  padding-top: 1.5rem;
  border-top: 1px solid #e2e8f0;
}

.btn {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.75rem 1.5rem;
  border-radius: 6px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn-update {
  background-color: #4299e1;
  color: white;
  border: none;
}

.btn-update:hover {
  background-color: #3182ce;
}

.btn-cancel {
  background-color: #edf2f7;
  color: #4a5568;
  border: 1px solid #e2e8f0;
}

.btn-cancel:hover {
  background-color: #e2e8f0;
}

@media (max-width: 768px) {
  .form-grid {
    grid-template-columns: 1fr;
  }
  
  .edit-container {
    padding: 1rem;
    margin-left: 0;
  }
}
</style>
<template>
   <div class="hoaDon">
    <h6>DANH SÁCH HÓA ĐƠN CHƯA THANH TOÁN</h6>
    <div class="dsHoaDon">
        <div class="timkiemHoaDon">
            <input type="text" v-model="tuKhoa" @keyup.enter="timKiemHoaDon" placeholder="Nhập từ khóa" />
            <button @click="timKiemHoaDon">Tìm kiếm hóa đơn</button>
        </div>
      <div class="table-hd">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>STT</th>
              <th>Mã HD</th>
              <th>Tên NV</th>
              <th>Mã KH</th>
              <th>Tên KH</th>
              <th>Ngày tạo</th>
              <th>Tổng tiền</th>
              <th>Trạng thái</th>
              <th colspan="2">Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(hoaDon, index) in danhSachHoaDon" :key="hoaDon.id">
              <td>{{ index + 1 }}</td>
              <td>{{ hoaDon.maHoaDon }}</td>
              <td>{{ hoaDon.nhanVien.hoVaTen }}</td>
              <td>{{ hoaDon.khachHang.maKhachHang }}</td>
              <td>{{ hoaDon.khachHang.hoTen }}</td>
              <td>{{ new Date(hoaDon.ngayTao).toLocaleDateString("vi-VN") }}</td>
              <td>{{ hoaDon.tongTien.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }) }}</td>
              <td>{{ hoaDon.trangThai }}</td>
              <td>
                  <button>Sửa</button>
                  <button>Xóa</button>
              </td>
            </tr>
            <tr v-if="danhSachHoaDon.length === 0">
              <td colspan="7">Không có hóa đơn chưa thanh toán</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="Spct">
    <h6>DANH SÁCH ÁO DÀI</h6>

    <div class="timkiemsp">
      <input type="text" v-model="keyWord" @keyup.enter="timKiem" placeholder="Nhập keyword" />
      <button @click="timKiem">Tìm kiếm</button>
    </div>

    <div class="table-ct">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>STT</th>
            <th>Mã áo dài CT</th>
            <th>Tên áo dài</th>
            <th>Màu sắc</th>
            <th>Kích thước</th>
            <th>Chất liệu</th>
            <th>Số lượng</th>
            <th>Giá bán</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(aoDaiCT, index) in danhSachAoDai" :key="aoDaiCT.id">
            <td>{{ index + 1 + page * size }}</td>
            <td>{{ aoDaiCT.maAoDaiChiTiet }}</td>
            <td>{{ aoDaiCT.aoDai.tenAoDai }}</td>
            <td>{{ aoDaiCT.mauSac.tenMauSac }}</td>
            <td>{{ aoDaiCT.kichThuoc.ten }}</td>
            <td>{{ aoDaiCT.aoDai.chatLieu.ten }}</td>
            <td>{{ aoDaiCT.soLuong }}</td>
            <td>{{ aoDaiCT.giaBan.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }) }}</td>
            <td><button>Thêm</button></td>
          </tr>
          <tr v-if="danhSachAoDai.length === 0">
            <td colspan="9">Không có dữ liệu phù hợp</td>
          </tr>
        </tbody>
      </table>
    </div>

<div class="phanTrang">
  <button @click="goToFirstPage" :disabled="page === 0">Trang đầu</button>
  <button @click="prevPage" :disabled="page === 0">Trước</button>
  <span>Trang {{ page + 1 }}/{{ totalPages }}</span>
  <button @click="nextPage" :disabled="page >= totalPages - 1">Sau</button>
  <button @click="goToLastPage" :disabled="page >= totalPages - 1">Trang cuối</button>
</div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      tuKhoa: "",
      keyWord: "", 
      danhSachAoDai: [],
      page: 0, 
      size: 4,
      totalPages: 0,
      danhSachHoaDon: [],
    };
  },
  methods: {
    async fetchDanhSachAoDai() {
      try {
        const response = await axios.get(
          "http://localhost:8080/ban-hang/ao-dai-chi-tiet/hien-thi/phan-trang",
          {
            params: {
              page: this.page,
              size: this.size,
              keyWord: this.keyWord || null,
            },
          }
        );
        this.danhSachAoDai = response.data.content;
        this.totalPages = response.data.totalPages;
      } catch (error) {
        console.error("Lỗi khi lấy dữ liệu áo dài:", error);
      }
    },
      // call api hd chưa thanh toán
    async fetchHoaDonChuaThanhToan() {
      try {
        const response = await axios.get(
          "http://localhost:8080/ban-hang/ds-hoa-don-chua-thanh-toan"
        );
        this.danhSachHoaDon = response.data;
      } catch (error) {
        console.error("Lỗi khi lấy danh sách hóa đơn chưa thanh toán:", error);
      }
    },
    async timKiemHoaDon() {
    try {
      const response = await axios.get(
        "http://localhost:8080/ban-hang/tim-kiem-hoa-don-chua-thanh-toan",
        {
          params: {
            tuKhoa: this.tuKhoa || null,
          },
        }
      );
      this.danhSachHoaDon = response.data;
    } catch (error) {
      console.error("Lỗi khi tìm kiếm hóa đơn:", error);
    }
  },
    timKiem() {
      this.page = 0;
      this.fetchDanhSachAoDai();
    },
    prevPage() {
      if (this.page > 0) {
        this.page--;
        this.fetchDanhSachAoDai();
      }
    },
    nextPage() {
      if (this.page < this.totalPages - 1) {
        this.page++;
        this.fetchDanhSachAoDai();
      }
    },
    goToFirstPage() {
      this.page = 0;
      this.fetchDanhSachAoDai();
    },
    goToLastPage() {
      this.page = this.totalPages - 1;
      this.fetchDanhSachAoDai();
    },
  },
  mounted() {
    this.fetchDanhSachAoDai();
    this.fetchHoaDonChuaThanhToan();
  },
};
</script>

<style scoped>
.Spct {
  margin-top: 200px; /*kc vs div trên*/
  margin-left: 235px;
  max-width: 1260px;
  font-size: 12px;
}
.timkiemsp {
  margin-bottom: 10px;
}
.timkiemsp input {
  width: 18%;
  padding: 3px;
  margin-right: 10px;
}
.timkiemsp button {
  padding: 3px 8px;
}
.table-ct {
  overflow-x: auto;
  width: 63%;
}
th,td {
  text-align: center;
}
th {
    background-color: #f1f1f1; /* Màu nền cho tiêu đề */
    position: sticky; /* Đảm bảo tiêu đề cố định khi cuộn */
    top: 0; /* Đặt tiêu đề luôn ở trên cùng */
    z-index: 1; /* Đảm bảo tiêu đề luôn ở trên cùng các dòng */
}
.phanTrang {
  display: flex;
  margin-left : 215px;
  gap: 7px;
}
.phanTrang button {
  padding: 4px 8px;
}
.phanTrang span {
  padding: 5px 10px;
}

.hoaDon{
  margin-left: 235px;
  max-width: 1260px;
  font-size: 12px;
}
.table-hd {
  max-height: 150px; /* Điều chỉnh chiều cao để hiển thị thanh cuộn dọc */
  overflow-y: auto;
  display: block;
  width: 63%;
}

.table-hd button{
  margin-left: 5px
}

</style>

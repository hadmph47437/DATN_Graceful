<template>
  <div class="Spct">
    <h5>Danh sách áo dài</h5>

    <div class="timkiemsp">
      <input type="text" v-model="keyWord" @keyup.enter="timKiem" placeholder="Nhập keyword" />
      <button @click="timKiem">Tìm kiếm</button>
    </div>

    <div class="table-ct">
      <table class="table table-bordered table-hover">
        <thead>
          <tr>
            <th>STT</th>
            <th>Mã áo dài chi tiết</th>
            <th>Tên áo dài</th>
            <th>Màu sắc</th>
            <th>Kích thước</th>
            <th>Chất liệu</th>
            <th>Giá bán</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(aoDai, index) in danhSachAoDai" :key="aoDai.id">
            <td>{{ index + 1 + page * size }}</td>
            <td>{{ aoDai.maAoDaiChiTiet }}</td>
            <td>{{ aoDai.aoDai.tenAoDai }}</td>
            <td>{{ aoDai.mauSac.tenMauSac }}</td>
            <td>{{ aoDai.kichThuoc.ten }}</td>
            <td>{{ aoDai.aoDai.chatLieu.ten }}</td>
            <td>{{ aoDai.giaBan.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }) }}</td>
          </tr>
          <tr v-if="danhSachAoDai.length === 0">
            <td colspan="7">Không có dữ liệu phù hợp</td>
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
      keyWord: "", // Từ khóa tìm kiếm
      danhSachAoDai: [], // Dữ liệu áo dài
      page: 0, // Trang hiện tại
      size: 5, // Số phần tử mỗi trang
      totalPages: 0, // Tổng số trang
    };
  },
  methods: {
    // Gọi API lấy danh sách áo dài
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
        this.totalPages = response.data.totalPages; // Lấy tổng số trang từ API
      } catch (error) {
        console.error("Lỗi khi lấy dữ liệu áo dài:", error);
      }
    },
    timKiem() {
      this.page = 0; // Reset về trang đầu tiên khi tìm kiếm
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
  },
};
</script>

<style scoped>
.Spct {
  margin-top: 350px;
  margin-left: 260px;
  max-width: 1260px;
  font-size: 13px;
}
.timkiemsp {
  margin-bottom: 15px;
}
.timkiemsp input {
  width: 40%;
  padding: 5px;
  margin-right: 10px;
}
.timkiemsp button {
  padding: 5px 10px;
}
.table-ct {
  overflow-x: auto;
  width: 60%;
}
th,
td {
  text-align: center;
}
.phanTrang {
  margin-top: 15px;
  display: flex;
  justify-content: center;
  gap: 10px;
}
.phanTrang button {
  padding: 5px 10px;
}
.phanTrang span {
  padding: 5px 10px;
}
</style>

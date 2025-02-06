<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2>Danh sách khách hàng</h2>
      <button @click="$router.push('/khach-hang/add')" class="btn btn-primary">
        <i class="bi bi-plus-circle me-2"></i>Thêm khách hàng
      </button>
    </div>
    <div class="mb-3">
      <input
        v-model="searchTerm"
        @keyup="handleSearch"
        class="form-control"
        placeholder="Tìm kiếm theo mã, tên, email, số điện thoại, tên đăng nhập"
      />
    </div>
    <table class="table table-bordered table-hover text-center">
      <thead>
        <tr>
          <th>ID</th>
          <th>Mã khách hàng</th>
          <th>Họ và tên</th>
          <th>Giới tính</th>
          <th>Ngày sinh</th>
          <th>Email</th>
          <th>SDT</th>
          <th>Tên đăng nhập</th>
          <th>Địa chỉ</th>
          <th>Ngày tạo</th>
          <th>Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="kh in khachHangs" :key="kh.id">
          <td>{{ kh.id }}</td>
          <td>{{ kh.maKhachHang }}</td>
          <td>{{ kh.hoTen }}</td>
          <td>{{ kh.gioiTinh ? "Nam" : "Nữ" }}</td>
          <td>{{ kh.ngaySinh }}</td>
          <td>{{ kh.email }}</td>
          <td>{{ kh.soDienThoai }}</td>
          <td>{{ kh.tenDangNhap }}</td>
          <td>
            {{ `${kh.diaChi_duong}, ${kh.diaChi_quan}, ${kh.diaChi_thanhPho}` }}
          </td>
          <td>{{ kh.ngayTao }}</td>
          <td class="d-flex justify-content-evenly">
            <button
              @click="editKhachHang(kh.id)"
              class="btn btn-warning btn-sm me-2"
            >
              <i class="bi bi-pen"></i>
            </button>
            <button
              @click="viewKhachHang(kh.id)"
              class="btn btn-success btn-sm"
            >
              <i class="bi bi-eye"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="container">
      <div class="row justify-content-center align-items-center">
        <div class="col-auto">
          <nav aria-label="Page navigation">
            <ul class="pagination mb-0">
              <li class="page-item" :class="{ disabled: currentPage === 0 }">
                <button class="page-link" @click="diToiTrangDau">First</button>
              </li>
              <li class="page-item" :class="{ disabled: currentPage === 0 }">
                <button class="page-link" @click="diToiTrangTruoc">Prev</button>
              </li>
              <li class="page-item active">
                <span class="page-link"
                  >{{ currentPage + 1 }} / {{ totalPages }}</span
                >
              </li>
              <li
                class="page-item"
                :class="{ disabled: currentPage >= totalPages - 1 }"
              >
                <button class="page-link" @click="diToiTrangSau">Next</button>
              </li>
              <li
                class="page-item"
                :class="{ disabled: currentPage >= totalPages - 1 }"
              >
                <button class="page-link" @click="diToiTrangCuoi">Last</button>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import KhachHangService from "../../services/KhachHangService";
export default {
  data() {
    return {
      khachHangs: [],
      diaChis: [],
      searchTerm: "",
      currentPage: 0,
      totalPages: 0,
      pageSize: 5,
      searchTimeout: null,
    };
  },
  created() {
    this.getAllKhachHangs();
  },
  methods: {
    getAllKhachHangs() {
        const searchParams={
          keyword: this.searchTerm,
          page: this.currentPage,
          size: this.pageSize,
        }
      KhachHangService.getAllKhachHang(searchParams)
        .then((response) => {
          this.khachHangs = response.data.content;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => console.error(error));
    },
    editKhachHang(id) {
      this.$router.push(`/khach-hang/update/${id}`);
    },
    viewKhachHang(id) {
      this.$router.push(`/khach-hang/view/${id}`);
    },
    handleSearch() {
      if (this.searchTimeout) {
        clearTimeout(this.searchTimeout);
      }
      this.searchTimeout = setTimeout(() =>{
         this.currentPage = 0; 
         this.getAllKhachHangs();
      },300)
      // this.currentPage = 0; 
      // this.getAllKhachHangs();
    },
    
    changePage(page) {
      if (page >= 0 && page < this.totalPages) {
        this.currentPage = page;
        this.getAllKhachHangs();
      }
    },
    diToiTrangDau() {
      this.currentPage = 0;
      this.getAllKhachHangs();
    },
    diToiTrangTruoc() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.getAllKhachHangs();
      }
    },
    diToiTrangSau() {
      if (this.currentPage < this.totalPages - 1) {
        this.currentPage++;
        this.getAllKhachHangs();
      }
    },
    diToiTrangCuoi() {
      this.currentPage = this.totalPages - 1;
      this.getAllKhachHangs();
    },
  },
};
</script>

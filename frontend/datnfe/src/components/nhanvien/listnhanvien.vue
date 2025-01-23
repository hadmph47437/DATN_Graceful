<template>
    <div class="container mt-5">
         <!-- Tìm kiếm -->
         <div class="mb-3">
            <label class="form-label">Tìm kiếm nhân viên</label>
            <input type="text" v-model="searchTerm" class="form-control" @input="searchNhanVien" placeholder="Tìm kiếm theo họ tên, email...">
        </div>
        <h2>Danh sách học sinh</h2>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã Nhân Viên</th>
                    <th>Tên Đăng Nhập</th>
                    <th>Họ tên</th>
                    <th>Giới Tính</th>
                    <th>Ngày Sinh</th>
                    <th>Email</th>
                    <th>Số Điện Thoại</th>
                    <th>Địa Chỉ</th>
                    <th>Trạng Thái</th>
                    <th>Chức Vụ</th>
                    <th colspan="4" >Action</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="nhanvien in nhanviens" :key="nhanvien.id">
                    <td>{{ nhanvien.id }}</td>
                    <td>{{nhanvien.maNhanVien }}</td>
                    <td>{{ nhanvien.tenDangNhap }}</td>
                    <td>{{ nhanvien.hoVaTen}}</td>
                    <td>{{ nhanvien.gioiTinh? 'Nam' : 'Nữ' }}</td>
                    <td>{{ nhanvien.ngaySinh}}</td>
                    <td>{{ nhanvien.email}}</td>
                    <td>{{ nhanvien.soDienThoai}}</td>
                    <td>{{ nhanvien.diaChi}}</td>
                    <td>{{ nhanvien.trangThai? 'Hoạt động' : 'Không hoạt động' }}</td>
                    <td>{{ nhanvien.vaiTro.tenVaiTro}}</td>
                    <td>
                        <button @click="editNhanVien(nhanvien.id)" class="btn btn-warning btn-sm me-2">Sửa</button>
                        <button @click="deleteNhanVien(nhanvien.id)" class="btn btn-danger btn-sm">Xóa</button>
                    </td>
                </tr>
            </tbody>
        </table>
        
        <button @click="navigateToAdd" class="btn btn-primary">Thêm nhân viên</button>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            nhanviens: [],
            searchTerm: '',
        };
    },
    created() {
        this.fetchHocSinhs();
    },
    methods: {
        fetchHocSinhs() {
            axios.get('http://localhost:8080/nhan-vien/hien-thi3')
                .then(response => this.nhanviens = response.data)
                .catch(error => console.error(error));
        },
        deleteNhanVien(id) {
            axios.delete(`http://localhost:8080/nhan-vien/delete/${id}`)
                .then(() => this.fetchHocSinhs())
                .catch(error => console.error(error));
        },
        editNhanVien(id) {
            this.$router.push(`/update/${id}`);
        },
         // Tìm kiếm nhân viên
       // Tìm kiếm nhân viên theo tên
// searchNhanVien() {
//     const searchParams = new URLSearchParams({ ho_va_ten: this.searchTerm }).toString();
//     axios.get(`http://localhost:8080/tim-kiem?${searchParams}`)
//         .then(response => {
//             this.nhanviens = response.data;
//         })
//         .catch(error => console.error(error));
// },
searchNhanVien() {
    const searchParams = new URLSearchParams({
        keyword: this.searchTerm
    }).toString();

    axios.get(`http://localhost:8080/tim-kiem?${searchParams}`)
        .then(response => {
            this.nhanviens = response.data;
        })
        .catch(error => console.error(error));
},
        navigateToAdd() {
            this.$router.push('/add');
        },
    },
};
</script>
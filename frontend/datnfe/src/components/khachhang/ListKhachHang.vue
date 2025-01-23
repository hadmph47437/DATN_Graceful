<template>
    <div class="container mt-4">
        <h2 class="">Danh sách khách hàng</h2>
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
                    <th>Mật khẩu</th>
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
                    <td>{{ kh.gioiTinh ? 'Nam':'Nữ' }}</td>
                    <td>{{ kh.ngaySinh }}</td>
                    <td>{{ kh.email }}</td>
                    <td>{{ kh.soDienThoai }}</td>
                    <td>{{ kh.tenDangNhap }}</td>
                    <td>{{ kh.matKhau }}</td>
                    <td>{{ kh.diaChis }}</td>
                    <td>{{ kh.ngayTao }}</td>
                    <td class="d-flex justify-content-evenly">
                        <button @click="editHocSinh(kh.id)" class="btn btn-warning btn-sm me-2"><i class="bi bi-pen"></i></button>
                        <button @click="deleteHocSinh(kh.id)" class="btn btn-success btn-sm"><i class="bi bi-eye"></i></button>
                    </td>
                </tr>
            </tbody>
        </table>
        <!-- <button @click="navigateToAdd" class="btn btn-primary">Thêm học sinh</button> -->
    </div>
</template>

<script>
import KhachHangService from '../../services/KhachHangService';
export default {
    data() {
        return {
            khachHangs: [],
        };
    },
    created() {
        this.getAllKhachHangs();
    },
    methods: {
        getAllKhachHangs() {
            KhachHangService.getAllKhachHang()
                .then(response => this.khachHangs = response.data)
                .catch(error => console.error(error));
        },
        // deleteHocSinh(id) {
        //     axios.delete(`http://localhost:8080/api/hoc-sinh/delete/${id}`)
        //         .then(() => this.fetchHocSinhs())
        //         .catch(error => console.error(error));
        // },
        // editHocSinh(id) {
        //     this.$router.push(`/edit/${id}`);
        // },
        // navigateToAdd() {
        //     this.$router.push('/add');
        // },
    },
};
</script>
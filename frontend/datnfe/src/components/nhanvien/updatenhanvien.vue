<template>
    <div class="container mt-4">
        
        <h2>update nhân viên</h2>

        <form @submit.prevent="updateNhanVien">
            <div class="mb-3">
                <label class="form-label">id</label>
                <input type="text" v-model="nhanVien.id" class="form-control" required readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">mã nhân viên</label>
                <input type="text" v-model="nhanVien.maNhanVien" class="form-control" required readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Họ Tên</label>
                <input type="text" v-model="nhanVien.hoVaTen" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Tên Đăng Nhập</label>
                <input type="text" v-model="nhanVien.tenDangNhap" class="form-control" required>
            </div>
            <div class="mb-3">
    <label class="form-label">Mật Khẩu</label>
    <input type="password" v-model="nhanVien.matKhau" class="form-control" required>
</div>
            <div class="mb-3">
                <label class="form-label">Giới Tính</label>
                <select v-model="nhanVien.gioiTinh" class="form-select" required>
                    <option value="" disabled>Chọn giới tính</option>
                    <option value="true">Nam</option>
                    <option value="false">Nữ</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Ngày Sinh</label>
                <input type="date" v-model="nhanVien.ngaySinh" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" v-model="nhanVien.email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Số Điện Thoại</label>
                <input type="text" v-model="nhanVien.soDienThoai" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Địa Chỉ</label>
                <input type="text" v-model="nhanVien.diaChi" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Chức Vụ</label>
                <select v-model="nhanVien.vaiTro.id" class="form-select" required>
                    <option value="" disabled>Chọn chức vụ</option>
                    <option v-for="vaiTro in vaiTros" :key="vaiTro.id" :value="vaiTro.id">
                        {{ vaiTro.tenVaiTro }}
                    </option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Trạng Thái</label>
                <select v-model="nhanVien.trangThai" class="form-select" required>
                    <option value="" disabled>Chọn trạng thái</option>
                    <option value="true">Hoạt động</option>
                    <option value="false">Không hoạt động</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Lưu</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            nhanVien: {
                id: '',
                maNhanVien: '',
                hoVaTen: '',
                tenDangNhap: '',
                matKhau: '',
                gioiTinh: null,
                ngaySinh: '',
                email: '',
                soDienThoai: '',
                diaChi: '',
                vaiTro: { id: null },
                trangThai: null,
            },
            vaiTros: [],
        };
    },
    // created() {
    //     const id = this.$route.params.id;
    //     axios.get(`http://localhost:8080/nhan-vien/hien-thi/${id}`)
    //         .then(response => 
            
    //             {
    //                 console.log(response.data);
    //                 const nv=response.data.find(h => h.id === Number(id));
    //             if (nv) this.nhanVien = nv; }
    //         )
    //         .catch(error => console.error(error));
    //     this.fetchVaiTros();
    // },
    created() {
    const id = this.$route.params.id;
    axios.get(`http://localhost:8080/nhan-vien/hien-thi/${id}`)
        .then(response => {
            console.log(response.data); // Kiểm tra dữ liệu trả về
            const nv = response.data; // Trả về 1 đối tượng, không cần tìm
            if (nv) {
                this.nhanVien = nv; // Gán trực tiếp nếu dữ liệu là 1 đối tượng nhân viên
            }
        })
        .catch(error => console.error(error));

    this.fetchVaiTros(); // Tải chức vụ
},

    methods: {
        fetchVaiTros() {
            axios.get('http://localhost:8080/nhan-vien/show-vai-tro')
                .then(response => this.vaiTros = response.data)
                .catch(error => console.error(error));
        },
        updateNhanVien() {
            axios.put('http://localhost:8080/nhan-vien/update', this.nhanVien)
                .then(() => this.$router.push('/'))
                .catch(error => console.error(error));
        },
        
        
    },
};
</script>

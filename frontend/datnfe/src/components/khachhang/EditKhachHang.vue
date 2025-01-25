<template>
    <div class="container mt-4">
        <h2 class="">Chỉnh sửa thông tin khách hàng</h2>
        <form @submit.prevent="updateKhachHang">
            <div class="mb-3">
                <label class="form-label">Họ và tên</label>
                <input type="text" v-model="khachHang.hoTen" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Ngày sinh</label>
                <input type="date" v-model="khachHang.ngaySinh" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" v-model="khachHang.email" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="tel" v-model="khachHang.soDienThoai" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Tên đăng nhập</label>
                <input type="text" v-model="khachHang.tenDangNhap" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Địa chỉ</label>
                <input type="text" v-model="khachHang.diaChi" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Giới tính</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="gioiTinhNam" value="Nam" v-model="khachHang.gioiTinh">
                    <label class="form-check-label" for="gioiTinhNam">Nam</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" id="gioiTinhNu" value="Nữ" v-model="khachHang.gioiTinh" checked>
                    <label class="form-check-label" for="gioiTinhNu">Nữ</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Cập nhật</button>
        </form>
    </div>
</template>

<script>
export default {
    
}
</script>
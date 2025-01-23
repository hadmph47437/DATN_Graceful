<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Trang quản lý</a>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav flex-column">
        <li class="nav-item">
          <router-link to="/ban-hang" class="nav-link">
            <i class="fas fa-store-alt"></i> Bán hàng
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/hoa-don/hien-thi" class="nav-link">
            <i class="fas fa-file-invoice"></i> Hóa Đơn
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/quan-ly-san-pham" class="nav-link">
            <i class="fas fa-cogs"></i> Quản lý sản phẩm
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/quan-ly-giam-gia" class="nav-link">
            <i class="fas fa-percent"></i> Quản lý giảm giá
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/quan-ly-khach-hang" class="nav-link">
            <i class="fas fa-users"></i> Khách hàng
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/quan-ly-nhan-vien" class="nav-link">
            <i class="fas fa-user-tie"></i> Quản lý nhân viên
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/thong-tin-ban-than" class="nav-link">
            <i class="fas fa-user-circle"></i> Thông tin bản thân
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/thong-ke" class="nav-link">
            <i class="fas fa-chart-line"></i> Thống kê
          </router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'Header',
};
</script>

<style scoped>
.navbar-nav {
  width: 250px; /* Chiều rộng của menu */
  position: fixed; /* Menu cố định */
  top: 0;
  left: 0;
  height: 100vh; /* Chiều cao menu bằng chiều cao màn hình */
  background-color:rgba(227, 225, 225, 0.99); /* Màu nền sáng cho menu */
  color: #333; /* Màu chữ tối cho dễ đọc */
  padding-top: 30px; /* Khoảng cách từ trên xuống */
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1); /* Bóng đổ cho menu */
}

.nav-item {
  margin-bottom: 20px; /* Khoảng cách giữa các mục */
}

.nav-link {
  font-size: 18px;
  color: #333;
  padding: 12px 20px; /* Điều chỉnh padding để menu đẹp hơn */
  border-radius: 5px;
  transition: background-color 0.3s ease, transform 0.3s ease; /* Hiệu ứng hover */
  display: flex;
  align-items: center; /* Căn giữa nội dung và icon */
}

.nav-link i {
  margin-right: 10px; /* Khoảng cách giữa icon và tên mục menu */
}

.nav-link:hover {
  background-color: #007bff; /* Màu nền khi hover */
  color: white;
  transform: translateX(5px); /* Di chuyển nhẹ sang phải khi hover */
}

.navbar-brand {
  position: absolute;
  top: 20px;
  left: 20px;
  font-size: 24px;
  font-weight: bold;
  color: #007bff; /* Màu chữ sáng cho logo */
}
</style>

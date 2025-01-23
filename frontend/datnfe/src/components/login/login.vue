// datnfe/src/components/login/login.vue

<template>
  <div class="login-container">
    <h2>Đăng nhập</h2>
    <form @submit.prevent="handleLogin">
      <div class="mb-3">
        <label for="username" class="form-label">Tên đăng nhập</label>
        <input
          type="text"
          class="form-control"
          id="username"
          v-model="username"
          required
        />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Mật khẩu</label>
        <input
          type="password"
          class="form-control"
          id="password"
          v-model="password"
          required
        />
      </div>
      <button type="submit" class="btn btn-primary">Đăng nhập</button>
      <p v-if="errorMessage" class="text-danger mt-2">{{ errorMessage }}</p>
    </form>

    <div
      v-if="successMessage"
      class="alert alert-success mt-3"
      role="alert"
    >
      {{ successMessage }}
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
      successMessage: '',
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/login', {
          tenDangNhap: this.username,
          matKhau: this.password,
        });

        const { hoVaTen, vaiTro } = response.data;
        this.successMessage = `Chúc mừng ${hoVaTen} đã đăng nhập thành công với vai trò ${vaiTro}`;
        this.errorMessage = '';

        // Giới hạn thời gian cho thông báo thành công trước khi chuyển trang
        setTimeout(() => {
          // Emit sự kiện đăng nhập thành công cho App.vue
          this.$emit('login-success');
          // Chuyển hướng sau 3 giây
          this.$router.push('/ban-hang');
        }, 3000);
        
      } catch (error) {
        this.errorMessage =
          error.response?.data || 'Đăng nhập không thành công';
        this.successMessage = '';
      }
    },
  },
};
</script>



<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.alert {
  padding: 15px;
  border: 1px solid transparent;
  border-radius: 5px;
  font-size: 14px;
}

.alert-success {
  color: #155724;
  background-color: #d4edda;
  border-color: #c3e6cb;
}
</style>


<template>
  <footer class="bg-dark text-white text-center py-3 mt-auto">
    <div class="container">
      <p>© 2025 Công ty XYZ | All Rights Reserved</p>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
};
</script>

<style scoped>
footer {
  background-color: #343a40; /* Màu nền tối cho footer */
  color: #fff; /* Màu chữ trắng */
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 20px 0; /* Padding để tạo không gian cho footer */
}

footer .container {
  max-width: 1200px;
  margin: 0 auto; /* Căn giữa nội dung */
}

footer p {
  margin: 0; /* Xóa margin cho đoạn văn */
}
</style>




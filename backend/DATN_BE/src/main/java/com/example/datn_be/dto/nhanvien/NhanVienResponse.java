package com.example.datn_be.dto.nhanvien;

import com.example.datn_be.entiy.vaitro;

public interface NhanVienResponse {
     Integer id();

   String maNhanVien();

     String tenDangNhap();

   String hoVaTen();

    Integer gioiTinh();

    String ngaySinh();

    String email();

     String matKhau();

     String soDienThoai();

    String diaChi();

   Boolean trangThai();

    vaitro vaiTro();

}

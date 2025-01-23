package com.example.datn_be.repository;

import com.example.datn_be.entiy.NhanVienBH;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhanVienRepo extends JpaRepository<NhanVienBH, Integer> {
    NhanVienBH findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
}

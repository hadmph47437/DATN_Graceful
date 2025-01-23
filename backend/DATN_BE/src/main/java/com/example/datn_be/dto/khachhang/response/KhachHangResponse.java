package com.example.datn_be.dto.khachhang.response;

import java.time.LocalDate;
import java.util.List;

public interface KhachHangResponse {

    Integer getId();

    String getMaKhachHang();

    String getHoTen();

    Boolean getGioiTinh();

    LocalDate getNgaySinh();

    String getEmail();

    String getSoDienThoai();

    String getTenDangNhap();

    String getMatKhau();

    LocalDate getNgayTao();

    List<DiaChiEditResponse> getDiaChis();

}

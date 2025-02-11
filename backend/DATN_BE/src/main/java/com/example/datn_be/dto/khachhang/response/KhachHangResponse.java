package com.example.datn_be.dto.khachhang.response;


import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface KhachHangResponse {

    Integer getId();

    String getMaKhachHang();

    String getHoTen();

    Boolean getGioiTinh();

    LocalDate getNgaySinh();

    String getEmail();

    String getSoDienThoai();


    LocalDate getNgayTao();

    @Value("#{target.diaChiId}")
    Integer getDiaChiId();

    String getDiaChi_duong();

    String getDiaChi_quan();

    String getDiaChi_thanhPho();

    String getDiaChi_tinh();

    Boolean getDiaChi_macDinh();

}

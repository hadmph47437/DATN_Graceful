package com.example.datn_be.repository;

import com.example.datn_be.dto.khachhang.response.KhachHangResponse;
import com.example.datn_be.entiy.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang, Integer> {
    @Query(value = """
    SELECT 
        kh.id AS id,
        kh.ma_khach_hang AS maKhachHang,
        kh.ho_ten AS hoTen,
        kh.gioi_tinh AS gioiTinh,
        kh.ngay_sinh AS ngaySinh,
        kh.email AS email,
        kh.so_dien_thoai AS soDienThoai,
        kh.ten_dang_nhap AS tenDangNhap,
        kh.ngay_tao AS ngayTao,
        dc.id AS diaChiId,
        dc.duong AS diaChi_duong,
        dc.quan AS diaChi_quan,
        dc.thanh_pho AS diaChi_thanhPho,
        dc.tinh AS diaChi_tinh,
        dc.mac_dinh AS diaChi_macDinh
    FROM khach_hang kh
    LEFT JOIN dia_chi dc ON dc.id_khach_hang = kh.id
    WHERE :keyword IS NULL 
        OR kh.ma_khach_hang LIKE %:keyword%
        OR kh.ho_ten LIKE %:keyword%
        OR kh.email LIKE %:keyword%
        OR kh.so_dien_thoai LIKE %:keyword%
        OR kh.ten_dang_nhap LIKE %:keyword%
    """, countQuery = """
    SELECT COUNT(DISTINCT kh.id)
    FROM khach_hang kh
    WHERE :keyword IS NULL 
        OR kh.ma_khach_hang LIKE %:keyword%
        OR kh.ho_ten LIKE %:keyword%
        OR kh.email LIKE %:keyword%
        OR kh.so_dien_thoai LIKE %:keyword%
        OR kh.ten_dang_nhap LIKE %:keyword%
    """, nativeQuery = true)
    Page<KhachHangResponse> getAllKhachHang(
            @Param("keyword") String keyword,
            Pageable pageable
    );

}

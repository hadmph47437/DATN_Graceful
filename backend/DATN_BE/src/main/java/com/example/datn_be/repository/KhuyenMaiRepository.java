package com.example.datn_be.repository;

import com.example.datn_be.entiy.KhuyenMai;
import com.example.datn_be.dto.khuyenmai.KhuyenMaiRespone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Integer> {
@Query("""
SELECT new  com.example.datn_be.dto.khuyenmai.KhuyenMaiRespone
    (

    kh.tenKhuyenMai,
    kh.phanTramGiam,
    kh.sanPhamChiTiet.maAoDaiChiTiet,
    kh.ngayBatDau,
    kh.ngayKetThuc,
    kh.trangThai,
    kh.moTa,
    kh.ngayTao
    )FROM KhuyenMai kh
        """)
    List<KhuyenMaiRespone>getAllRespone();
}

package com.example.datn_be.repository;

import com.example.datn_be.entiy.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoaDonRepo extends JpaRepository<HoaDon, Integer> {
    @Query("SELECT h FROM HoaDon h WHERE " +
            "(:searchQuery IS NULL OR h.maHoaDon LIKE %:searchQuery% OR h.khachHang.soDienThoai LIKE %:searchQuery%)")
    Page<HoaDon> timKiemHoaDon(@Param("searchQuery") String searchQuery, Pageable pageable);

    Page<HoaDon> findByTrangThai(String trangThai, Pageable pageable);

}

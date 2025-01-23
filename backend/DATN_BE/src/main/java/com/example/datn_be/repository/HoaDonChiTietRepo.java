package com.example.datn_be.repository;

import com.example.datn_be.entiy.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet, Integer> {
    @Query("SELECT h FROM HoaDonChiTiet h WHERE h.hoaDon.id = :idHoaDon")
    List<HoaDonChiTiet> findByHoaDonId(@Param("idHoaDon") Integer idHoaDon);
}

package com.example.datn_be.repository;

import com.example.datn_be.entiy.LoaiSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Integer> {

    Page<LoaiSanPham> findByTrangThaiTrue(Pageable pageable);

    Page<LoaiSanPham> findByTrangThaiFalse(Pageable pageable);

    Page<LoaiSanPham> findByTenLoaiAoDaiContainingIgnoreCase(String ten, Pageable pageable);

    Page<LoaiSanPham> findByTenLoaiAoDaiContainingIgnoreCaseAndTrangThai(String ten, Boolean trangThai, Pageable pageable);

}

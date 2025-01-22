package com.example.datn_be.repository;

import com.example.datn_be.entiy.NhaCungCap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Integer> {

    Page<NhaCungCap> findByTrangThaiTrue(Pageable pageable);

    Page<NhaCungCap> findByTrangThaiFalse(Pageable pageable);

    Page<NhaCungCap> findByTenNhaCungCapContainingIgnoreCase(String ten, Pageable pageable);

    Page<NhaCungCap> findByTenNhaCungCapContainingIgnoreCaseAndTrangThai(String ten, Boolean trangThai, Pageable pageable);

}

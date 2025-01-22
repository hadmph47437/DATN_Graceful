package com.example.datn_be.repository;

import com.example.datn_be.entiy.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {

    Page<MauSac> findByTrangThaiTrue(Pageable pageable);

    Page<MauSac> findByTrangThaiFalse(Pageable pageable);

    Page<MauSac> findByTenMauSacContainingIgnoreCase(String tenMauSac, Pageable pageable);

    Page<MauSac> findByTenMauSacContainingIgnoreCaseAndTrangThai(String tenMauSac, Boolean trangThai, Pageable pageable);

}

package com.example.datn_be.repository;

import com.example.datn_be.entiy.KichThuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuoc, Integer> {

    Page<KichThuoc> findByTrangThaiTrue(Pageable pageable);

    Page<KichThuoc> findByTrangThaiFalse(Pageable pageable);

    Page<KichThuoc> findByTenContainingIgnoreCase(String ten, Pageable pageable);

    Page<KichThuoc> findByTenContainingIgnoreCaseAndTrangThai(String ten, Boolean trangThai, Pageable pageable);

}

package com.example.datn_be.repository;

import com.example.datn_be.entiy.TaAo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaAoRepository extends JpaRepository<TaAo, Integer> {

    Page<TaAo> findByTrangThaiTrue(Pageable pageable);

    Page<TaAo> findByTrangThaiFalse(Pageable pageable);

    Page<TaAo> findByTenContainingIgnoreCase(String ten, Pageable pageable);

    Page<TaAo> findByTenContainingIgnoreCaseAndTrangThai(String ten, Boolean trangThai, Pageable pageable);


}

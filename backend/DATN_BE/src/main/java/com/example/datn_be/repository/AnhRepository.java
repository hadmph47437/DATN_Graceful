package com.example.datn_be.repository;

import com.example.datn_be.entiy.Anh;
import com.example.datn_be.entiy.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnhRepository extends JpaRepository<Anh, Integer> {
    @Modifying
    @Query("DELETE FROM Anh a WHERE a.aoDai = :aoDai")
    void deleteAllByAoDai(@Param("aoDai") SanPham aoDai);

}

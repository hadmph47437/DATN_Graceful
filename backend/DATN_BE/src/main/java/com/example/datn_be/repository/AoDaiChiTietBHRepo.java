package com.example.datn_be.repository;

import com.example.datn_be.entiy.AoDaiChiTietBH;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AoDaiChiTietBHRepo extends JpaRepository<AoDaiChiTietBH, Integer> {
    @Query("""
    SELECT a FROM AoDaiChiTietBH a 
    JOIN a.aoDai ad 
    JOIN a.mauSac ms 
    JOIN a.kichThuoc kt 
    JOIN ad.chatLieu cl 
    WHERE (:keyWord IS NULL OR 
           LOWER(a.maAoDaiChiTiet) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR 
           LOWER(ad.tenAoDai) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR 
           LOWER(ms.tenMauSac) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR 
           LOWER(kt.ten) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR 
           LOWER(cl.ten) LIKE LOWER(CONCAT('%', :keyWord, '%')))
""")
    Page<AoDaiChiTietBH> timKiemSanPhamTheoKeyWord(@Param("keyWord") String keyWord, Pageable pageable);


}

package com.example.datn_be.repository;

import com.example.datn_be.entiy.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query("SELECT s FROM SanPham s " +
            "WHERE (:loaiSanPhamId IS NULL OR s.loaiAoDai.id = :loaiSanPhamId) " +
            "AND (:chatLieuId IS NULL OR s.chatLieu.id = :chatLieuId) " +
            "AND (:taAoId IS NULL OR s.taAo.id = :taAoId) " +
            "AND (:nhaCungCapId IS NULL OR s.nhaCungCap.id = :nhaCungCapId) " +
            "AND (:trangThai IS NULL OR s.trangThai = :trangThai)")
    Page<SanPham> filterSanPhamWithPagination(
            @Param("loaiSanPhamId") Integer loaiSanPhamId,
            @Param("chatLieuId") Integer chatLieuId,
            @Param("taAoId") Integer taAoId,
            @Param("nhaCungCapId") Integer nhaCungCapId,
            @Param("trangThai") Boolean trangThai,
            Pageable pageable
    );

    Page<SanPham> findByTrangThaiTrue(Pageable pageable);

    Page<SanPham> findByTrangThaiFalse(Pageable pageable);

    @Query(value = "SELECT * FROM ao_dai sp " +
            "WHERE (:keyword IS NULL OR (" +
            "LOWER(sp.ten_ao_dai) COLLATE Latin1_General_CI_AI LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(sp.ma_ao_dai) COLLATE Latin1_General_CI_AI LIKE LOWER(CONCAT('%', :keyword, '%')))) " +
            "AND sp.trang_thai = :trangThai", nativeQuery = true)
    Page<SanPham> searchByKeywordWithTrangThai(@Param("keyword") String keyword,
                                  @Param("trangThai") Boolean trangThai,
                                  Pageable pageable);

    @Query("SELECT s FROM SanPham s " +
            "WHERE LOWER(s.maAoDai) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.tenAoDai) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<SanPham> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}

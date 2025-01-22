package com.example.datn_be.repository;

import com.example.datn_be.entiy.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    @Query("SELECT spct FROM SanPhamChiTiet spct " +
            "WHERE (:sanPhamId IS NULL OR spct.aoDai.id = :sanPhamId) " +
            "AND (:mauSacId IS NULL OR spct.mauSac.id = :mauSacId) " +
            "AND (:kichThuocId IS NULL OR spct.kichThuoc.id = :kichThuocId) " +
            "AND (:minGiaBan IS NULL OR spct.giaBan >= :minGiaBan) " +
            "AND (:maxGiaBan IS NULL OR spct.giaBan <= :maxGiaBan) " +
            "AND (:minSoLuong IS NULL OR spct.soLuong >= :minSoLuong) " +
            "AND (:maxSoLuong IS NULL OR spct.soLuong <= :maxSoLuong) " +
            "AND (:trangThai IS NULL OR spct.trangThai = :trangThai)")
    Page<SanPhamChiTiet> filterSanPhamChiTietWithPagination(
            @Param("sanPhamId") Integer sanPhamId,
            @Param("mauSacId") Integer mauSacId,
            @Param("kichThuocId") Integer kichThuocId,
            @Param("minGiaBan") Double minGiaBan,
            @Param("maxGiaBan") Double maxGiaBan,
            @Param("minSoLuong") Integer minSoLuong,
            @Param("maxSoLuong") Integer maxSoLuong,
            @Param("trangThai") Boolean trangThai,
            Pageable pageable
    );

    Page<SanPhamChiTiet> findByTrangThaiTrue (Pageable pageable);

    Page<SanPhamChiTiet> findByTrangThaiFalse (Pageable pageable);

    @Query("SELECT spct FROM SanPhamChiTiet spct " +
            "WHERE (:keyword IS NULL OR " +
            "LOWER(spct.maAoDaiChiTiet) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(spct.aoDai.maAoDai) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(spct.aoDai.tenAoDai) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            // Cách sắp xếp 1: Tên áo dài + Size + Màu
            "LOWER(CONCAT(spct.aoDai.tenAoDai, " +
            "       CASE WHEN spct.kichThuoc.ten IS NOT NULL THEN CONCAT(' size ', spct.kichThuoc.ten) ELSE '' END, " +
            "       CASE WHEN spct.mauSac.tenMauSac IS NOT NULL THEN CONCAT(' mau ', spct.mauSac.tenMauSac) ELSE '' END" +
            ")) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            // Cách sắp xếp 2: Tên áo dài + Màu + Size
            "LOWER(CONCAT(spct.aoDai.tenAoDai, " +
            "       CASE WHEN spct.mauSac.tenMauSac IS NOT NULL THEN CONCAT(' mau ', spct.mauSac.tenMauSac) ELSE '' END, " +
            "       CASE WHEN spct.kichThuoc.ten IS NOT NULL THEN CONCAT(' size ', spct.kichThuoc.ten) ELSE '' END" +
            ")) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:trangThai IS NULL OR spct.trangThai = :trangThai)")
    Page<SanPhamChiTiet> searchByKeywordWithTrangThai(
            @Param("keyword") String keyword,
            @Param("trangThai") Boolean trangThai,
            Pageable pageable);

    @Query("SELECT spct FROM SanPhamChiTiet spct " +
            "WHERE (:keyword IS NULL OR " +
            "LOWER(spct.maAoDaiChiTiet) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(spct.aoDai.maAoDai) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(spct.aoDai.tenAoDai) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            // Cách sắp xếp 1: Tên áo dài + Size + Màu
            "LOWER(CONCAT(spct.aoDai.tenAoDai, " +
            "       CASE WHEN spct.kichThuoc.ten IS NOT NULL THEN CONCAT(' size ', spct.kichThuoc.ten) ELSE '' END, " +
            "       CASE WHEN spct.mauSac.tenMauSac IS NOT NULL THEN CONCAT(' mau ', spct.mauSac.tenMauSac) ELSE '' END" +
            ")) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            // Cách sắp xếp 2: Tên áo dài + Màu + Size
            "LOWER(CONCAT(spct.aoDai.tenAoDai, " +
            "       CASE WHEN spct.mauSac.tenMauSac IS NOT NULL THEN CONCAT(' mau ', spct.mauSac.tenMauSac) ELSE '' END, " +
            "       CASE WHEN spct.kichThuoc.ten IS NOT NULL THEN CONCAT(' size ', spct.kichThuoc.ten) ELSE '' END" +
            ")) LIKE LOWER(CONCAT('%', :keyword, '%'))) ")
    Page<SanPhamChiTiet> searchByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable);

}

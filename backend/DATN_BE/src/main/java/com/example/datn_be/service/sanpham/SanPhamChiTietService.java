package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.SanPhamChiTietDTO;
import org.springframework.data.domain.Page;

public interface SanPhamChiTietService {

    Page<SanPhamChiTietDTO> getListSanPhamChiTietDTOPage(int page, int size);

    Page<SanPhamChiTietDTO> filterSanPhamChiTietDTO(
            Integer sanPhamId,
            Integer mauSacId,
            Integer kichThuocId,
            Double minGiaBan,
            Double maxGiaBan,
            Integer minSoLuong,
            Integer maxSoLuong,
            Boolean trangThai,
            int page,
            int size
    );

    Page<SanPhamChiTietDTO> getSanPhamChiTietDTOPageWithTrangThaiTrue(int page, int size);

    Page<SanPhamChiTietDTO> getSanPhamChiTietDTOPageWithTrangThaiFalse( int page, int size);

    Page<SanPhamChiTietDTO> searchSanPhamChiTietsByKeyword(String keyword, int page, int size);

    Page<SanPhamChiTietDTO> searchSanPhamChiTietsWithTrangThaiTrue(String keyword, int page, int size);

    Page<SanPhamChiTietDTO> searchSanPhamChiTietsWithTrangThaiFalse(String keyword, int page, int size);

    SanPhamChiTietDTO addSanPhamChiTiet(SanPhamChiTietDTO sanPhamChiTietDTO);

    SanPhamChiTietDTO updateSanPhamChiTiet(int id, SanPhamChiTietDTO sanPhamChiTietDTO);

    SanPhamChiTietDTO getSanPhamChiTietById(int id);
}
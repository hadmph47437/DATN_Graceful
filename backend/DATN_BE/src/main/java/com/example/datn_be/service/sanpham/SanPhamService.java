package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.SanPhamDTO;
import org.springframework.data.domain.Page;

public interface SanPhamService {

    Page<SanPhamDTO> getSanPhamDTOPage(int page, int size);
    Page<SanPhamDTO> getSanPhamDTOPageWithTrangThaiTrue(int page, int size);

    Page<SanPhamDTO> getSanPhamDTOPageWithTrangThaiFalse(int page, int size);

    Page<SanPhamDTO> searchSanPhamByKeyWordWithTrangThaiTrue(String keyword, int page, int size);

    Page<SanPhamDTO> searchSanPhamByKeyWordWithTrangThaiFalse(String keyword, int page, int size);

    Page<SanPhamDTO> searchSanPhamByKeyWord(String keyword, int page, int size);

    Page<SanPhamDTO> filterSanPham(
            Integer loaiSanPhamId,
            Integer chatLieuId,
            Integer taAoId,
            Integer nhaCungCapId,
            Boolean trangThai,
            int page,
            int size
    );


    SanPhamDTO getSanPhamById(int id);

    SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO);

    SanPhamDTO updateSanPham(int id, SanPhamDTO sanPhamDTO);
}
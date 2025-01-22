package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.LoaiSanPhamDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoaiSanPhamService {

    Page<LoaiSanPhamDTO> getLoaiSanPhamDTOPage(int page, int size);

    Page<LoaiSanPhamDTO> getLoaiSanPhamDTOPageWithTrangThaiTrue(int page, int size);

    Page<LoaiSanPhamDTO> getLoaiSanPhamDTOPageWithTrangThaiFalse(int page, int size);

    Page<LoaiSanPhamDTO> searchLoaiSanPhamPageByKeyWordWithTrangThaiTrue(String keyword, int page, int size);

    Page<LoaiSanPhamDTO> searchLoaiSanPhamPageByKeyWordWithTrangThaiFalse(String keyword, int page, int size);

    Page<LoaiSanPhamDTO> searchLoaiSanPhamPageByKeyWord(String keyword, int page, int size);

    LoaiSanPhamDTO addLoaiSanPham(LoaiSanPhamDTO loaiSanPhamDTO);

    LoaiSanPhamDTO updateLoaiSanPham(int id, LoaiSanPhamDTO loaiSanPhamDTO);

    LoaiSanPhamDTO getLoaiSanPhamById(int id);
}
package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.KichThuocDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KichThuocService {

    Page<KichThuocDTO> getKichThuocDTOPage(int page, int size);

    Page<KichThuocDTO> getKichThuocDTOPageWithTrangThaiTrue(int page, int size);

    Page<KichThuocDTO> getKichThuocDTOPageWithTrangThaiFalse(int page, int size);

    Page<KichThuocDTO> searchKichThuocPageByKeyWordWithTrangThaiTrue(String keyword, int page, int size);

    Page<KichThuocDTO> searchKichThuocPageByKeyWordWithTrangThaiFalse(String keyword, int page, int size);

    Page<KichThuocDTO> searchKichThuocPageByKeyWord(String keyword, int page, int size);

    KichThuocDTO addKichThuoc(KichThuocDTO kichThuocDTO);

    KichThuocDTO updateKichThuoc(int id, KichThuocDTO kichThuocDTO);

    KichThuocDTO getKichThuocById(int id);
}
package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.NhaCungCapDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NhaCungCapService {

    Page<NhaCungCapDTO> getNhaCungCapDTOPage(int page, int size);

    Page<NhaCungCapDTO> getNhaCungCapDTOPageWithTrangThaiTrue(int page, int size);

    Page<NhaCungCapDTO> getNhaCungCapDTOPageWithTrangThaiFalse(int page, int size);

    Page<NhaCungCapDTO> searchNhaCungCapPageByKeyWordWithTrangThaiTrue(String keyword, int page, int size);

    Page<NhaCungCapDTO> searchNhaCungCapPageByKeyWordWithTrangThaiFalse(String keyword, int page, int size);

    Page<NhaCungCapDTO> searchNhaCungCapPageByKeyWord(String keyword, int page, int size);

    NhaCungCapDTO addNhaCungCap(NhaCungCapDTO nhaCungCapDTO);

    NhaCungCapDTO updateNhaCungCap(int id, NhaCungCapDTO nhaCungCapDTO);

    NhaCungCapDTO getNhaCungCapById(int id);
}
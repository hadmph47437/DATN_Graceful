package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.MauSacDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MauSacService {

    Page<MauSacDTO> getMauSacDTOPage(int page, int size);

    Page<MauSacDTO> getMauSacDTOPageWithTrangThaiTrue(int page, int size);

    Page<MauSacDTO> getMauSacDTOPageWithTrangThaiFalse(int page, int size);

    Page<MauSacDTO> searchMauSacPageByKeyWordWithTrangThaiTrue(String keyword, int page, int size);

    Page<MauSacDTO> searchMauSacPageByKeyWordWithTrangThaiFalse(String keyword, int page, int size);

    Page<MauSacDTO> searchMauSacPageByKeyWord(String keyword, int page, int size);

    MauSacDTO addMauSac(MauSacDTO mauSacDTO);

    MauSacDTO updateMauSac(int id, MauSacDTO mauSacDTO);

    MauSacDTO getMauSacById(int id);
}
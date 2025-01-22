package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.TaAoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaAoService {

    Page<TaAoDTO> getTaAoDTOPage(int page, int size);

    Page<TaAoDTO> getTaAoDTOPageWithTrangThaiTrue(int page, int size);

    Page<TaAoDTO> getTaAoDTOPageWithTrangThaiFalse(int page, int size);

    Page<TaAoDTO> searchTaAoByKeyWord(String keyword, int page, int size);

    Page<TaAoDTO> searchTaAoByKeyWordWithTrangThaiTrue(String keyword, int page, int size);

    Page<TaAoDTO> searchTaAoByKeyWordWithTrangThaiFalse(String keyword, int page, int size);

    TaAoDTO getTaAoById(int id);

    TaAoDTO addTaAo(TaAoDTO taAoDTO);

    TaAoDTO updateTaAo(int id, TaAoDTO taAoDTO);
}
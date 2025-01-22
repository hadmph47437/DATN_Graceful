package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.AnhDTO;

public interface AnhService {

    AnhDTO createAnh(AnhDTO anhDTO);

    AnhDTO updateAnh(Integer id, AnhDTO anhDTO);

    void deleteAnh(Integer id);

}

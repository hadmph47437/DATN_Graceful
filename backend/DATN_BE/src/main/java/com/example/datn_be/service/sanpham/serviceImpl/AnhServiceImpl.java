package com.example.datn_be.service.sanpham.serviceImpl;

import com.example.datn_be.dto.sanpham.AnhDTO;
import com.example.datn_be.entiy.Anh;
import com.example.datn_be.entiy.SanPham;
import com.example.datn_be.repository.AnhRepository;
import com.example.datn_be.repository.SanPhamRepository;
import com.example.datn_be.service.sanpham.AnhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class AnhServiceImpl implements AnhService {

    @Autowired
    private AnhRepository anhRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public static AnhDTO toDTO(Anh anh) {
        if (anh == null) {
            return null;
        }

        return new AnhDTO(
                anh.getId(),
                anh.getAnhUrl(),
                anh.getNgayTao(),
                anh.getTrangThai(),
                anh.getAoDai() != null ? anh.getAoDai().getId() : null
        );
    }

    public static Anh toEntity(AnhDTO anhDTO) {
        if (anhDTO == null) {
            return null;
        }

        Anh anh = new Anh();
        anh.setId(anhDTO.getId());
        anh.setAnhUrl(anhDTO.getAnhUrl());
        anh.setNgayTao(anhDTO.getNgayTao());
        anh.setTrangThai(anhDTO.getTrangThai());

        if (anhDTO.getSanPhamId() != null) {
            SanPham sanPham = new SanPham();
            sanPham.setId(anhDTO.getSanPhamId());
            anh.setAoDai(sanPham);
        }

        return anh;
    }

    @Override
    public AnhDTO createAnh(AnhDTO anhDTO) {
        Anh anh = toEntity(anhDTO);

        if (anhDTO.getSanPhamId() != null) {
            sanPhamRepository.findById(anhDTO.getSanPhamId()).orElseThrow(() ->
                    new IllegalArgumentException("Sản phẩm với ID " + anhDTO.getSanPhamId() + " không tồn tại."));
        }

        anh.setNgayTao(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

        Anh savedAnh = anhRepository.save(anh);

        return toDTO(savedAnh);
    }

    @Override
    public AnhDTO updateAnh(Integer id, AnhDTO anhDTO) {
        // Tìm ảnh theo ID
        Anh existingAnh = anhRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Ảnh với ID " + id + " không tồn tại."));

        if (anhDTO.getAnhUrl() != null) {
            existingAnh.setAnhUrl(anhDTO.getAnhUrl());
        }
        if (anhDTO.getTrangThai() != null) {
            existingAnh.setTrangThai(anhDTO.getTrangThai());
        }
        if (anhDTO.getSanPhamId() != null) {
            SanPham sanPham = sanPhamRepository.findById(anhDTO.getSanPhamId()).orElseThrow(() ->
                    new IllegalArgumentException("Sản phẩm với ID " + anhDTO.getSanPhamId() + " không tồn tại."));
            existingAnh.setAoDai(sanPham);
        }

        Anh updatedAnh = anhRepository.save(existingAnh);

        return toDTO(updatedAnh);
    }

    @Override
    public void deleteAnh(Integer id) {
        Anh anh = anhRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Ảnh với ID " + id + " không tồn tại."));

        anhRepository.delete(anh);
    }
}

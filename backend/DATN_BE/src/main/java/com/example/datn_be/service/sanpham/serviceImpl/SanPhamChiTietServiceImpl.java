package com.example.datn_be.service.sanpham.serviceImpl;

import com.example.datn_be.dto.sanpham.SanPhamChiTietDTO;
import com.example.datn_be.entiy.KichThuoc;
import com.example.datn_be.entiy.MauSac;
import com.example.datn_be.entiy.SanPham;
import com.example.datn_be.entiy.SanPhamChiTiet;
import com.example.datn_be.repository.SanPhamChiTietRepository;
import com.example.datn_be.service.sanpham.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    // Method chuyển từ Entity -> DTO
    private static SanPhamChiTietDTO toDTO(SanPhamChiTiet sanPhamChiTiet) {
        if (sanPhamChiTiet == null) {
            return null;
        }
        return new SanPhamChiTietDTO(
                sanPhamChiTiet.getId(),
                sanPhamChiTiet.getMaAoDaiChiTiet(),
                sanPhamChiTiet.getGiaGoc(),
                sanPhamChiTiet.getGiaBan(),
                sanPhamChiTiet.getSoLuong(),
                sanPhamChiTiet.getTrangThai(),
                sanPhamChiTiet.getAnhUrl(),
                sanPhamChiTiet.getNgayTao(),
                sanPhamChiTiet.getAoDai() != null ? sanPhamChiTiet.getAoDai().getId() : null,
                sanPhamChiTiet.getAoDai() != null ? sanPhamChiTiet.getAoDai().getMaAoDai() : null,
                sanPhamChiTiet.getAoDai() != null ? sanPhamChiTiet.getAoDai().getTenAoDai() : null,
                sanPhamChiTiet.getMauSac() != null ? sanPhamChiTiet.getMauSac().getId() : null,
                sanPhamChiTiet.getMauSac() != null ? sanPhamChiTiet.getMauSac().getTenMauSac() : null,
                sanPhamChiTiet.getKichThuoc() != null ? sanPhamChiTiet.getKichThuoc().getId() : null,
                sanPhamChiTiet.getKichThuoc() != null ? sanPhamChiTiet.getKichThuoc().getTen() : null
        );
    }

    // Method chuyển từ DTO -> Entity
    private static SanPhamChiTiet toEntity(SanPhamChiTietDTO dto) {
        if (dto == null) {
            return null;
        }
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        sanPhamChiTiet.setId(dto.getId());
        sanPhamChiTiet.setMaAoDaiChiTiet(dto.getMaAoDaiChiTiet());
        sanPhamChiTiet.setGiaGoc(dto.getGiaGoc());
        sanPhamChiTiet.setGiaBan(dto.getGiaBan());
        sanPhamChiTiet.setSoLuong(dto.getSoLuong());
        sanPhamChiTiet.setTrangThai(dto.getTrangThai());
        sanPhamChiTiet.setAnhUrl(dto.getAnhUrl());
        sanPhamChiTiet.setNgayTao(dto.getNgayTao());

        if (dto.getAoDaiId() != null) {
            SanPham sanPham = new SanPham();
            sanPham.setId(dto.getAoDaiId());
            sanPhamChiTiet.setAoDai(sanPham);
        }

        if (dto.getMauSacId() != null) {
            MauSac mauSac = new MauSac();
            mauSac.setId(dto.getMauSacId());
            sanPhamChiTiet.setMauSac(mauSac);
        }

        if (dto.getKichThuocId() != null) {
            KichThuoc kichThuoc = new KichThuoc();
            kichThuoc.setId(dto.getKichThuocId());
            sanPhamChiTiet.setKichThuoc(kichThuoc);
        }

        return sanPhamChiTiet;
    }

    public static String removeAccentAndToLower(String input) {
        if (input == null) {
            return null;
        }

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String noAccent = normalized.replaceAll("\\p{M}", "");
        String[] tokens = noAccent.trim().toLowerCase().split("\\s+");

        String tokensCombined = String.join("%", tokens);

        return tokensCombined.toLowerCase();
    }

    @Override
    public Page<SanPhamChiTietDTO> getListSanPhamChiTietDTOPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTiet> sanPhamChiTietPage = sanPhamChiTietRepository.findAll(pageable);
        return sanPhamChiTietPage.map(SanPhamChiTietServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamChiTietDTO> filterSanPhamChiTietDTO(
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
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.filterSanPhamChiTietWithPagination(
                sanPhamId, mauSacId, kichThuocId, minGiaBan, maxGiaBan, minSoLuong, maxSoLuong, trangThai, pageable);
        return sanPhamChiTiets.map(SanPhamChiTietServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamChiTietDTO> getSanPhamChiTietDTOPageWithTrangThaiTrue(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.findByTrangThaiTrue(pageable);
        return sanPhamChiTiets.map(SanPhamChiTietServiceImpl::toDTO);
    }
    @Override
    public Page<SanPhamChiTietDTO> getSanPhamChiTietDTOPageWithTrangThaiFalse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.findByTrangThaiFalse(pageable);
        return sanPhamChiTiets.map(SanPhamChiTietServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamChiTietDTO> searchSanPhamChiTietsByKeyword(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.searchByKeyword(removeAccentAndToLower(keyword), pageable);
        return sanPhamChiTiets.map(SanPhamChiTietServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamChiTietDTO> searchSanPhamChiTietsWithTrangThaiTrue(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.searchByKeywordWithTrangThai(removeAccentAndToLower(keyword), true, pageable);
        return sanPhamChiTiets.map(SanPhamChiTietServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamChiTietDTO> searchSanPhamChiTietsWithTrangThaiFalse(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.searchByKeywordWithTrangThai(removeAccentAndToLower(keyword), false, pageable);
        return sanPhamChiTiets.map(SanPhamChiTietServiceImpl::toDTO);
    }

    @Override
    public SanPhamChiTietDTO addSanPhamChiTiet(SanPhamChiTietDTO sanPhamChiTietDTO) {
        SanPhamChiTiet sanPhamChiTiet = toEntity(sanPhamChiTietDTO);

        sanPhamChiTiet.setNgayTao(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

        SanPhamChiTiet savedSanPhamChiTiet = sanPhamChiTietRepository.save(sanPhamChiTiet);

        return toDTO(savedSanPhamChiTiet);
    }

    @Override
    public SanPhamChiTietDTO updateSanPhamChiTiet(int id, SanPhamChiTietDTO sanPhamChiTietDTO) {
        Optional<SanPhamChiTiet> existingSanPhamChiTietOptional = sanPhamChiTietRepository.findById(id);

        if (existingSanPhamChiTietOptional.isPresent()) {
            SanPhamChiTiet existingSanPhamChiTiet = existingSanPhamChiTietOptional.get();

            // Cập nhật thông tin cơ bản, trừ các trường tự động
            existingSanPhamChiTiet.setGiaGoc(sanPhamChiTietDTO.getGiaGoc());
            existingSanPhamChiTiet.setGiaBan(sanPhamChiTietDTO.getGiaBan());
            existingSanPhamChiTiet.setSoLuong(sanPhamChiTietDTO.getSoLuong());
            existingSanPhamChiTiet.setTrangThai(sanPhamChiTietDTO.getTrangThai());
            existingSanPhamChiTiet.setAnhUrl(sanPhamChiTietDTO.getAnhUrl());

            // Cập nhật khóa ngoại nếu tồn tại
            if (sanPhamChiTietDTO.getAoDaiId() != null) {
                SanPham sanPham = new SanPham();
                sanPham.setId(sanPhamChiTietDTO.getAoDaiId());
                existingSanPhamChiTiet.setAoDai(sanPham);
            }

            if (sanPhamChiTietDTO.getMauSacId() != null) {
                MauSac mauSac = new MauSac();
                mauSac.setId(sanPhamChiTietDTO.getMauSacId());
                existingSanPhamChiTiet.setMauSac(mauSac);
            }

            if (sanPhamChiTietDTO.getKichThuocId() != null) {
                KichThuoc kichThuoc = new KichThuoc();
                kichThuoc.setId(sanPhamChiTietDTO.getKichThuocId());
                existingSanPhamChiTiet.setKichThuoc(kichThuoc);
            }

            SanPhamChiTiet updatedSanPhamChiTiet = sanPhamChiTietRepository.save(existingSanPhamChiTiet);

            return toDTO(updatedSanPhamChiTiet);
        } else {
            return null;
        }
    }

    @Override
    public SanPhamChiTietDTO getSanPhamChiTietById(int id) {
        Optional<SanPhamChiTiet> sanPhamChiTietOptional = sanPhamChiTietRepository.findById(id);
        return sanPhamChiTietOptional.map(SanPhamChiTietServiceImpl::toDTO).orElse(null);
    }
}

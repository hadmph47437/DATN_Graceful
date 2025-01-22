package com.example.datn_be.service.sanpham.serviceImpl;

import com.example.datn_be.dto.sanpham.KichThuocDTO;
import com.example.datn_be.entiy.KichThuoc;
import com.example.datn_be.repository.KichThuocRepository;
import com.example.datn_be.service.sanpham.KichThuocService;
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
public class KichThuocServiceImpl implements KichThuocService {

    @Autowired
    private KichThuocRepository kichThuocRepository;

    public static KichThuocDTO toDTO(KichThuoc kichThuoc) {
        if (kichThuoc == null) {
            return null;
        }
        return new KichThuocDTO(
                kichThuoc.getId(),
                kichThuoc.getTen(),
                kichThuoc.getMoTa(),
                kichThuoc.getTrangThai(),
                kichThuoc.getNgayTao()
        );
    }

    public static KichThuoc toEntity(KichThuocDTO dto) {
        if (dto == null) {
            return null;
        }
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setId(dto.getId());
        kichThuoc.setTen(dto.getTenKichThuoc());
        kichThuoc.setMoTa(dto.getMoTa());
        kichThuoc.setTrangThai(dto.getTrangThai());
        kichThuoc.setNgayTao(dto.getNgayTao());
        return kichThuoc;
    }

    public static String removeAccentAndToLower(String input) {
        if (input == null) {
            return null;
        }

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String noAccent = normalized.replaceAll("\\p{M}", "");

        return noAccent.toLowerCase();
    }

    @Override
    public Page<KichThuocDTO> getKichThuocDTOPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KichThuoc> kichThuocPage = kichThuocRepository.findAll(pageable);
        return kichThuocPage.map(KichThuocServiceImpl::toDTO);
    }

    @Override
    public Page<KichThuocDTO> getKichThuocDTOPageWithTrangThaiTrue(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KichThuoc> kichThuocPage = kichThuocRepository.findByTrangThaiTrue(pageable);
        return kichThuocPage.map(KichThuocServiceImpl::toDTO);
    }

    @Override
    public Page<KichThuocDTO> getKichThuocDTOPageWithTrangThaiFalse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KichThuoc> kichThuocPage = kichThuocRepository.findByTrangThaiFalse(pageable);
        return kichThuocPage.map(KichThuocServiceImpl::toDTO);
    }

    @Override
    public Page<KichThuocDTO> searchKichThuocPageByKeyWordWithTrangThaiTrue(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KichThuoc> kichThuocPage = kichThuocRepository.findByTenContainingIgnoreCaseAndTrangThai(removeAccentAndToLower(keyword), true, pageable);
        return kichThuocPage.map(KichThuocServiceImpl::toDTO);
    }

    @Override
    public Page<KichThuocDTO> searchKichThuocPageByKeyWordWithTrangThaiFalse(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KichThuoc> kichThuocPage = kichThuocRepository.findByTenContainingIgnoreCaseAndTrangThai(removeAccentAndToLower(keyword), false, pageable);
        return kichThuocPage.map(KichThuocServiceImpl::toDTO);
    }

    @Override
    public Page<KichThuocDTO> searchKichThuocPageByKeyWord(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KichThuoc> kichThuocPage = kichThuocRepository.findByTenContainingIgnoreCase(removeAccentAndToLower(keyword), pageable);
        return kichThuocPage.map(KichThuocServiceImpl::toDTO);
    }

    @Override
    public KichThuocDTO addKichThuoc(KichThuocDTO kichThuocDTO) {
        KichThuoc kichThuoc = toEntity(kichThuocDTO);
        kichThuoc.setNgayTao(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        KichThuoc savedKichThuoc = kichThuocRepository.save(kichThuoc);
        return toDTO(savedKichThuoc);
    }

    @Override
    public KichThuocDTO updateKichThuoc(int id, KichThuocDTO kichThuocDTO) {
        Optional<KichThuoc> existingKichThuocOptional = kichThuocRepository.findById(id);

        if (existingKichThuocOptional.isPresent()) {
            KichThuoc existingKichThuoc = existingKichThuocOptional.get();

            // Cập nhật các trường cần thiết
            existingKichThuoc.setTen(kichThuocDTO.getTenKichThuoc());
            existingKichThuoc.setMoTa(kichThuocDTO.getMoTa());
            existingKichThuoc.setTrangThai(kichThuocDTO.getTrangThai());

            // Lưu lại vào cơ sở dữ liệu
            KichThuoc updatedKichThuoc = kichThuocRepository.save(existingKichThuoc);

            return toDTO(updatedKichThuoc);
        } else {
            return null;
        }
    }

    @Override
    public KichThuocDTO getKichThuocById(int id) {
        Optional<KichThuoc> kichThuocOptional = kichThuocRepository.findById(id);
        if (kichThuocOptional.isPresent()) {
            KichThuoc kichThuoc = kichThuocOptional.get();
            return toDTO(kichThuoc);
        } else {
            return null;
        }
    }

}
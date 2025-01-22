package com.example.datn_be.service.sanpham.serviceImpl;

import com.example.datn_be.dto.sanpham.NhaCungCapDTO;
import com.example.datn_be.entiy.NhaCungCap;
import com.example.datn_be.repository.NhaCungCapRepository;
import com.example.datn_be.service.sanpham.NhaCungCapService;
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
public class NhaCungCapServiceImpl implements NhaCungCapService {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    // Converter Entity -> DTO
    private static NhaCungCapDTO toDTO(NhaCungCap nhaCungCap) {
        if (nhaCungCap == null) {
            return null;
        }
        return new NhaCungCapDTO(
                nhaCungCap.getId(),
                nhaCungCap.getTenNhaCungCap(),
                nhaCungCap.getDiaChi(),
                nhaCungCap.getSoDienThoai(),
                nhaCungCap.getEmail(),
                nhaCungCap.getTrangThai(),
                nhaCungCap.getMoTa(),
                nhaCungCap.getNgayTao()
        );
    }

    // Converter DTO -> Entity
    private static NhaCungCap toEntity(NhaCungCapDTO dto) {
        if (dto == null) {
            return null;
        }
        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.setId(dto.getId());
        nhaCungCap.setTenNhaCungCap(dto.getTenNhaCungCap());
        nhaCungCap.setDiaChi(dto.getDiaChi());
        nhaCungCap.setSoDienThoai(dto.getSoDienThoai());
        nhaCungCap.setEmail(dto.getEmail());
        nhaCungCap.setTrangThai(dto.getTrangThai());
        nhaCungCap.setMoTa(dto.getMoTa());
        nhaCungCap.setNgayTao(dto.getNgayTao());
        return nhaCungCap;
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
    public Page<NhaCungCapDTO> getNhaCungCapDTOPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaCungCap> nhaCungCapPage = nhaCungCapRepository.findAll(pageable);
        return nhaCungCapPage.map(NhaCungCapServiceImpl::toDTO);
    }

    @Override
    public Page<NhaCungCapDTO> getNhaCungCapDTOPageWithTrangThaiTrue(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaCungCap> nhaCungCapPage = nhaCungCapRepository.findByTrangThaiTrue(pageable);
        return nhaCungCapPage.map(NhaCungCapServiceImpl::toDTO);
    }

    @Override
    public Page<NhaCungCapDTO> getNhaCungCapDTOPageWithTrangThaiFalse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaCungCap> nhaCungCapPage = nhaCungCapRepository.findByTrangThaiFalse(pageable);
        return nhaCungCapPage.map(NhaCungCapServiceImpl::toDTO);
    }

    @Override
    public Page<NhaCungCapDTO> searchNhaCungCapPageByKeyWordWithTrangThaiTrue(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaCungCap> nhaCungCapPage = nhaCungCapRepository.findByTenNhaCungCapContainingIgnoreCaseAndTrangThai(removeAccentAndToLower(keyword), true, pageable);
        return nhaCungCapPage.map(NhaCungCapServiceImpl::toDTO);
    }

    @Override
    public Page<NhaCungCapDTO> searchNhaCungCapPageByKeyWordWithTrangThaiFalse(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaCungCap> nhaCungCapPage = nhaCungCapRepository.findByTenNhaCungCapContainingIgnoreCaseAndTrangThai(removeAccentAndToLower(keyword), false, pageable);
        return nhaCungCapPage.map(NhaCungCapServiceImpl::toDTO);
    }

    @Override
    public Page<NhaCungCapDTO> searchNhaCungCapPageByKeyWord(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhaCungCap> nhaCungCapPage = nhaCungCapRepository.findByTenNhaCungCapContainingIgnoreCase(removeAccentAndToLower(keyword), pageable);
        return nhaCungCapPage.map(NhaCungCapServiceImpl::toDTO);
    }

    @Override
    public NhaCungCapDTO addNhaCungCap(NhaCungCapDTO nhaCungCapDTO) {
        NhaCungCap nhaCungCap = toEntity(nhaCungCapDTO);

        // Gán ngày tạo là ngày hiện tại
        nhaCungCap.setNgayTao(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

        // Lưu vào cơ sở dữ liệu
        NhaCungCap savedNhaCungCap = nhaCungCapRepository.save(nhaCungCap);

        return toDTO(savedNhaCungCap);
    }

    @Override
    public NhaCungCapDTO updateNhaCungCap(int id, NhaCungCapDTO nhaCungCapDTO) {
        Optional<NhaCungCap> existingNhaCungCapOptional = nhaCungCapRepository.findById(id);

        if (existingNhaCungCapOptional.isPresent()) {
            NhaCungCap existingNhaCungCap = existingNhaCungCapOptional.get();

            // Cập nhật các trường cần thiết
            existingNhaCungCap.setTenNhaCungCap(nhaCungCapDTO.getTenNhaCungCap());
            existingNhaCungCap.setDiaChi(nhaCungCapDTO.getDiaChi());
            existingNhaCungCap.setSoDienThoai(nhaCungCapDTO.getSoDienThoai());
            existingNhaCungCap.setEmail(nhaCungCapDTO.getEmail());
            existingNhaCungCap.setTrangThai(nhaCungCapDTO.getTrangThai());
            existingNhaCungCap.setMoTa(nhaCungCapDTO.getMoTa());

            // Lưu lại vào cơ sở dữ liệu
            NhaCungCap updatedNhaCungCap = nhaCungCapRepository.save(existingNhaCungCap);

            return toDTO(updatedNhaCungCap);
        } else {
            return null;
        }
    }

    @Override
    public NhaCungCapDTO getNhaCungCapById(int id) {
        Optional<NhaCungCap> nhaCungCapOptional = nhaCungCapRepository.findById(id);
        if (nhaCungCapOptional.isPresent()) {
            NhaCungCap nhaCungCap = nhaCungCapOptional.get();
            return toDTO(nhaCungCap);
        } else {
            return null;
        }
    }
}

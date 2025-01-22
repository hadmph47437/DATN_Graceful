package com.example.datn_be.service.sanpham.serviceImpl;

import com.example.datn_be.dto.sanpham.AnhDTO;
import com.example.datn_be.dto.sanpham.SanPhamDTO;
import com.example.datn_be.entiy.*;
import com.example.datn_be.repository.AnhRepository;
import com.example.datn_be.repository.SanPhamRepository;
import com.example.datn_be.service.sanpham.AnhService;
import com.example.datn_be.service.sanpham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private AnhRepository anhRepository;

    @Autowired
    private AnhService anhService;

    // Chuyển từ Entity sang DTO
    public static SanPhamDTO toDTO(SanPham sanPham) {
        if (sanPham == null) {
            return null;
        }

        List<String> anhUrls = Optional.ofNullable(sanPham.getAnhList())
                .orElseGet(List::of)
                .stream()
                .map(Anh::getAnhUrl)
                .toList();

        return new SanPhamDTO(
                sanPham.getId(),
                sanPham.getMaAoDai(),
                sanPham.getTenAoDai(),
                sanPham.getMoTa(),
                sanPham.getTrangThai(),
                sanPham.getNgayTao(),
                sanPham.getLoaiAoDai() != null ? sanPham.getLoaiAoDai().getId() : null,
                sanPham.getLoaiAoDai() != null ? sanPham.getLoaiAoDai().getTenLoaiAoDai() : null,
                sanPham.getChatLieu() != null ? sanPham.getChatLieu().getId() : null,
                sanPham.getChatLieu() != null ? sanPham.getChatLieu().getTen() : null,
                sanPham.getTaAo() != null ? sanPham.getTaAo().getId() : null,
                sanPham.getTaAo() != null ? sanPham.getTaAo().getTen() : null,
                sanPham.getNhaCungCap() != null ? sanPham.getNhaCungCap().getId() : null,
                sanPham.getNhaCungCap() != null ? sanPham.getNhaCungCap().getTenNhaCungCap() : null,
                anhUrls // Gán danh sách URL
        );
    }

    // Chuyển từ DTO sang Entity
    public static SanPham toEntity(SanPhamDTO dto) {
        if (dto == null) {
            return null;
        }
        SanPham sanPham = new SanPham();
        sanPham.setId(dto.getId());
        sanPham.setMaAoDai(dto.getMaAoDai());
        sanPham.setTenAoDai(dto.getTenAoDai());
        sanPham.setMoTa(dto.getMoTa());
        sanPham.setTrangThai(dto.getTrangThai());
        sanPham.setNgayTao(dto.getNgayTao());

        checkIdValid(dto, sanPham);

        return sanPham;
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
    public Page<SanPhamDTO> getSanPhamDTOPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamPage = sanPhamRepository.findAll(pageable);
        return sanPhamPage.map(SanPhamServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamDTO> getSanPhamDTOPageWithTrangThaiTrue(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamPage = sanPhamRepository.findByTrangThaiTrue(pageable);
        return sanPhamPage.map(SanPhamServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamDTO> getSanPhamDTOPageWithTrangThaiFalse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamPage = sanPhamRepository.findByTrangThaiFalse(pageable);
        return sanPhamPage.map(SanPhamServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamDTO> searchSanPhamByKeyWordWithTrangThaiTrue(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sanPhamRepository.searchByKeywordWithTrangThai(removeAccentAndToLower(keyword), true, pageable).map(SanPhamServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamDTO> searchSanPhamByKeyWordWithTrangThaiFalse(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sanPhamRepository.searchByKeywordWithTrangThai(removeAccentAndToLower(keyword), false, pageable).map(SanPhamServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamDTO> searchSanPhamByKeyWord(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sanPhamRepository.searchByKeyword(removeAccentAndToLower(keyword), pageable).map(SanPhamServiceImpl::toDTO);
    }

    @Override
    public Page<SanPhamDTO> filterSanPham(
            Integer loaiSanPhamId,
            Integer chatLieuId,
            Integer taAoId,
            Integer nhaCungCapId,
            Boolean trangThai,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhams = sanPhamRepository.filterSanPhamWithPagination(loaiSanPhamId, chatLieuId, taAoId, nhaCungCapId, trangThai, pageable);
        return sanPhams.map(SanPhamServiceImpl::toDTO);
    }

    @Override
    public SanPhamDTO getSanPhamById(int id) {
        Optional<SanPham> sanPham = sanPhamRepository.findById(id);
        return sanPham.map(SanPhamServiceImpl::toDTO).orElse(null);
    }

    @Override
    @Transactional
    public SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO) {
        SanPham sanPham = toEntity(sanPhamDTO);
        sanPham.setNgayTao(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        SanPham savedSanPham = sanPhamRepository.save(sanPham);

        // Lưu danh sách ảnh từ URL
        createAnh(sanPhamDTO, savedSanPham);
        return toDTO(savedSanPham);
    }

    @Override
    @Transactional
    public SanPhamDTO updateSanPham(int id, SanPhamDTO sanPhamDTO) {
        Optional<SanPham> existingSanPhamOptional = sanPhamRepository.findById(id);

        if (existingSanPhamOptional.isPresent()) {
            SanPham existingSanPham = existingSanPhamOptional.get();

            existingSanPham.setTenAoDai(sanPhamDTO.getTenAoDai());
            existingSanPham.setMoTa(sanPhamDTO.getMoTa());
            existingSanPham.setTrangThai(sanPhamDTO.getTrangThai());

            checkIdValid(sanPhamDTO, existingSanPham);

            anhRepository.deleteAllByAoDai(existingSanPham);

            createAnh(sanPhamDTO, existingSanPham);
            SanPham updatedSanPham = sanPhamRepository.save(existingSanPham);

            return toDTO(updatedSanPham);
        } else {
            return null;
        }
    }

    private static void checkIdValid(SanPhamDTO sanPhamDTO, SanPham existingSanPham) {
        if (sanPhamDTO.getLoaiAoDaiId() != null) {
            LoaiSanPham loaiSanPham = new LoaiSanPham();
            loaiSanPham.setId(sanPhamDTO.getLoaiAoDaiId());
            existingSanPham.setLoaiAoDai(loaiSanPham);
        }

        if (sanPhamDTO.getChatLieuId() != null) {
            ChatLieu chatLieu = new ChatLieu();
            chatLieu.setId(sanPhamDTO.getChatLieuId());
            existingSanPham.setChatLieu(chatLieu);
        }

        if (sanPhamDTO.getTaAoId() != null) {
            TaAo taAo = new TaAo();
            taAo.setId(sanPhamDTO.getTaAoId());
            existingSanPham.setTaAo(taAo);
        }

        if (sanPhamDTO.getNhaCungCapId() != null) {
            NhaCungCap nhaCungCap = new NhaCungCap();
            nhaCungCap.setId(sanPhamDTO.getNhaCungCapId());
            existingSanPham.setNhaCungCap(nhaCungCap);
        }
    }

    private void createAnh(SanPhamDTO sanPhamDTO, SanPham existingSanPham) {
        for (String url : sanPhamDTO.getAnhList()) {
            AnhDTO anh = new AnhDTO();
            anh.setAnhUrl(url);
            anh.setTrangThai(true);
            anh.setNgayTao(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
            anh.setSanPhamId(existingSanPham.getId());
            anhService.createAnh(anh);
        }
    }


}


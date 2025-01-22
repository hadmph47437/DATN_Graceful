package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.SanPhamChiTietDTO;
import com.example.datn_be.service.sanpham.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/san-pham-chi-tiet")
@CrossOrigin("*")
public class SanPhamChiTietController {

    private final SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    public SanPhamChiTietController(SanPhamChiTietService sanPhamChiTietService) {
        this.sanPhamChiTietService = sanPhamChiTietService;
    }

    @GetMapping("/search")
    public Page<SanPhamChiTietDTO> searchSanPhamChiTiets(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return sanPhamChiTietService.searchSanPhamChiTietsByKeyword(keyword, page, size);
    }

    @GetMapping("/search-e")
    public Page<SanPhamChiTietDTO> searchSanPhamChiTietEnables(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return sanPhamChiTietService.searchSanPhamChiTietsWithTrangThaiTrue(keyword, page, size);
    }

    @GetMapping("/search-d")
    public Page<SanPhamChiTietDTO> searchSanPhamChiTietDisables(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return sanPhamChiTietService.searchSanPhamChiTietsWithTrangThaiFalse(keyword, page, size);
    }

    @GetMapping("/list/all")
    public ResponseEntity<Page<SanPhamChiTietDTO>> getSanPhamChiTiets(@RequestParam(defaultValue = "0" ) int page,
                                                                             @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(sanPhamChiTietService.getListSanPhamChiTietDTOPage(page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<SanPhamChiTietDTO>> getSanPhamChiTietDisables(@RequestParam(defaultValue = "0" ) int page,
                                                                            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(sanPhamChiTietService.getSanPhamChiTietDTOPageWithTrangThaiFalse(page, size));
    }

    @GetMapping("/list/enables")
    public ResponseEntity<Page<SanPhamChiTietDTO>> getSanPhamChiTietEnables(@RequestParam(defaultValue = "0" ) int page,
                                                                            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(sanPhamChiTietService.getSanPhamChiTietDTOPageWithTrangThaiTrue(page, size));
    }

    @GetMapping("/filter")
    public Page<SanPhamChiTietDTO> filterSanPhamChiTietDTO(
            @RequestParam(required = false) Integer sanPhamId,
            @RequestParam(required = false) Integer mauSacId,
            @RequestParam(required = false) Integer kichThuocId,
            @RequestParam(required = false) Double minGiaBan,
            @RequestParam(required = false) Double maxGiaBan,
            @RequestParam(required = false) Integer minSoLuong,
            @RequestParam(required = false) Integer maxSoLuong,
            @RequestParam(required = false) Boolean trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return sanPhamChiTietService.filterSanPhamChiTietDTO(
                sanPhamId, mauSacId, kichThuocId, minGiaBan, maxGiaBan, minSoLuong, maxSoLuong, trangThai, page, size
        );
    }

    @PostMapping("/add")
    public ResponseEntity<SanPhamChiTietDTO> addSanPhamChiTiet(@RequestBody SanPhamChiTietDTO sanPhamChiTietDTO) {
        SanPhamChiTietDTO savedSanPhamChiTiet = sanPhamChiTietService.addSanPhamChiTiet(sanPhamChiTietDTO);
        return ResponseEntity.ok(savedSanPhamChiTiet);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SanPhamChiTietDTO> updateSanPhamChiTiet(@PathVariable int id, @RequestBody SanPhamChiTietDTO sanPhamChiTietDTO) {
        SanPhamChiTietDTO updatedSanPhamChiTiet = sanPhamChiTietService.updateSanPhamChiTiet(id, sanPhamChiTietDTO);
        if (updatedSanPhamChiTiet != null) {
            return ResponseEntity.ok(updatedSanPhamChiTiet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SanPhamChiTietDTO> getSanPhamChiTietById(@PathVariable int id) {
        SanPhamChiTietDTO sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(id);
        if (sanPhamChiTiet != null) {
            return ResponseEntity.ok(sanPhamChiTiet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


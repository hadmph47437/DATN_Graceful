package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.SanPhamDTO;
import com.example.datn_be.service.sanpham.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/san-pham")
@CrossOrigin("*")
public class SanPhamController {

    private final SanPhamService sanPhamService;

    @Autowired
    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping("/list/all")
    public ResponseEntity<Page<SanPhamDTO>> getSanPhamsPage(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(sanPhamService.getSanPhamDTOPage(page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<SanPhamDTO>> getSanPhamsPageDisables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(sanPhamService.getSanPhamDTOPageWithTrangThaiFalse(page, size));
    }

    @GetMapping("/list/enables")
    public ResponseEntity<Page<SanPhamDTO>> getSanPhamsPageEnables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(sanPhamService.getSanPhamDTOPageWithTrangThaiTrue(page, size));
    }

    @GetMapping("/filter")
    public Page<SanPhamDTO> filterSanPham(
            @RequestParam(required = false) Integer loaiSanPhamId,
            @RequestParam(required = false) Integer chatLieuId,
            @RequestParam(required = false) Integer taAoId,
            @RequestParam(required = false) Integer nhaCungCapId,
            @RequestParam(required = false) Boolean trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5" ) int size
    ) {
        return sanPhamService.filterSanPham(loaiSanPhamId, chatLieuId, taAoId, nhaCungCapId, trangThai, page, size);
    }

    @GetMapping("/search")
    public Page<SanPhamDTO> searchSanPhams(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return sanPhamService.searchSanPhamByKeyWord(keyword, page, size);
    }

    @GetMapping("/search-e")
    public Page<SanPhamDTO> searchSanPhamsEn(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return sanPhamService.searchSanPhamByKeyWordWithTrangThaiTrue(keyword, page, size);
    }

    @GetMapping("/search-d")
    public Page<SanPhamDTO> searchSanPhamsDis(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return sanPhamService.searchSanPhamByKeyWordWithTrangThaiFalse(keyword, page, size);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SanPhamDTO> getSanPhamById(@PathVariable int id) {
        SanPhamDTO sanPhamDTO = sanPhamService.getSanPhamById(id);
        if (sanPhamDTO != null) {
            return ResponseEntity.ok(sanPhamDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<SanPhamDTO> addSanPham(@RequestBody SanPhamDTO sanPhamDTO) {
        SanPhamDTO savedSanPhamDTO = sanPhamService.addSanPham(sanPhamDTO);
        return ResponseEntity.ok(savedSanPhamDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SanPhamDTO> updateSanPham(@PathVariable int id, @RequestBody SanPhamDTO sanPhamDTO) {
        SanPhamDTO updatedSanPhamDTO = sanPhamService.updateSanPham(id, sanPhamDTO);
        if (updatedSanPhamDTO != null) {
            return ResponseEntity.ok(updatedSanPhamDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

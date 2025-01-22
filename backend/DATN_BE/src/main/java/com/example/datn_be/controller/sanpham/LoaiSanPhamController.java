package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.LoaiSanPhamDTO;
import com.example.datn_be.service.sanpham.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loai-san-pham")
@CrossOrigin("*")
public class LoaiSanPhamController {

    private final LoaiSanPhamService loaiSanPhamService;

    @Autowired
    public LoaiSanPhamController(LoaiSanPhamService loaiSanPhamService) {
        this.loaiSanPhamService = loaiSanPhamService;
    }

    @GetMapping("/search-e")
    public ResponseEntity<Page<LoaiSanPhamDTO>> searchLoaiSanPhamsEn(@RequestParam(defaultValue = "0" ) int page,
                                                                @RequestParam(defaultValue = "5") int size,
                                                                @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(loaiSanPhamService.searchLoaiSanPhamPageByKeyWordWithTrangThaiTrue(keyword, page, size));
    }

    @GetMapping("/search-d")
    public ResponseEntity<Page<LoaiSanPhamDTO>> searchLoaiSanPhamsDis(@RequestParam(defaultValue = "0" ) int page,
                                                                     @RequestParam(defaultValue = "5") int size,
                                                                     @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(loaiSanPhamService.searchLoaiSanPhamPageByKeyWordWithTrangThaiFalse(keyword, page, size));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<LoaiSanPhamDTO>> searchLoaiSanPhams(@RequestParam(defaultValue = "0" ) int page,
                                                                      @RequestParam(defaultValue = "5") int size,
                                                                      @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(loaiSanPhamService.searchLoaiSanPhamPageByKeyWord(keyword, page, size));
    }
    @GetMapping("/list/all")
    public ResponseEntity<Page<LoaiSanPhamDTO>> getLoaiSanPhams(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(loaiSanPhamService.getLoaiSanPhamDTOPage(page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<LoaiSanPhamDTO>> getLoaiSanPhamDisables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(loaiSanPhamService.getLoaiSanPhamDTOPageWithTrangThaiFalse(page, size));
    }

 @GetMapping("/list/enables")
    public ResponseEntity<Page<LoaiSanPhamDTO>> getLoaiSanPhamEnables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(loaiSanPhamService.getLoaiSanPhamDTOPageWithTrangThaiTrue(page, size));
    }

    @PostMapping("/add")
    public ResponseEntity<LoaiSanPhamDTO> addLoaiSanPham(@RequestBody LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPhamDTO savedLoaiSanPham = loaiSanPhamService.addLoaiSanPham(loaiSanPhamDTO);
        return ResponseEntity.ok(savedLoaiSanPham);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoaiSanPhamDTO> updateLoaiSanPham(@PathVariable int id, @RequestBody LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPhamDTO updatedLoaiSanPham = loaiSanPhamService.updateLoaiSanPham(id, loaiSanPhamDTO);
        if (updatedLoaiSanPham != null) {
            return ResponseEntity.ok(updatedLoaiSanPham);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<LoaiSanPhamDTO> getLoaiSanPhamById(@PathVariable int id) {
        LoaiSanPhamDTO loaiSanPhamDTO = loaiSanPhamService.getLoaiSanPhamById(id);
        return loaiSanPhamDTO != null ? ResponseEntity.ok(loaiSanPhamDTO) : ResponseEntity.notFound().build();
    }
}


package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.NhaCungCapDTO;
import com.example.datn_be.service.sanpham.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nha-cung-cap")
@CrossOrigin("*")
public class NhaCungCapController {

    @Autowired
    private NhaCungCapService nhaCungCapService;

    @GetMapping("/search-e")
    public ResponseEntity<Page<NhaCungCapDTO>> searchNhaCungCapsEn(@RequestParam(defaultValue = "0" ) int page,
                                                                 @RequestParam(defaultValue = "5") int size,
                                                                 @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(nhaCungCapService.searchNhaCungCapPageByKeyWordWithTrangThaiTrue(keyword, page, size));
    }

    @GetMapping("/search-d")
    public ResponseEntity<Page<NhaCungCapDTO>> searchNhaCungCapsDis(@RequestParam(defaultValue = "0" ) int page,
                                                                   @RequestParam(defaultValue = "5") int size,
                                                                   @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(nhaCungCapService.searchNhaCungCapPageByKeyWordWithTrangThaiFalse(keyword, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NhaCungCapDTO>> searchNhaCungCaps(@RequestParam(defaultValue = "0" ) int page,
                                                                    @RequestParam(defaultValue = "5") int size,
                                                                    @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(nhaCungCapService.searchNhaCungCapPageByKeyWord(keyword, page, size));
    }

    @GetMapping("/list/all")
    public ResponseEntity<Page<NhaCungCapDTO>> getNhaCungCaps(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(nhaCungCapService.getNhaCungCapDTOPage(page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<NhaCungCapDTO>> getNhaCungCapDisables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(nhaCungCapService.getNhaCungCapDTOPageWithTrangThaiFalse(page, size));
    }

    @GetMapping("/list/enables")
    public ResponseEntity<Page<NhaCungCapDTO>> getNhaCungCapEnables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(nhaCungCapService.getNhaCungCapDTOPageWithTrangThaiTrue(page, size));
    }

    @PostMapping("/add")
    public ResponseEntity<NhaCungCapDTO> addNhaCungCap(@RequestBody NhaCungCapDTO nhaCungCapDTO) {
        NhaCungCapDTO savedNhaCungCap = nhaCungCapService.addNhaCungCap(nhaCungCapDTO);
        return ResponseEntity.ok(savedNhaCungCap);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NhaCungCapDTO> updateNhaCungCap(@PathVariable int id, @RequestBody NhaCungCapDTO nhaCungCapDTO) {
        NhaCungCapDTO updatedNhaCungCap = nhaCungCapService.updateNhaCungCap(id, nhaCungCapDTO);
        if (updatedNhaCungCap != null) {
            return ResponseEntity.ok(updatedNhaCungCap);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<NhaCungCapDTO> getNhaCungCapById(@PathVariable int id) {
        NhaCungCapDTO nhaCungCapDTO = nhaCungCapService.getNhaCungCapById(id);
        return nhaCungCapDTO != null ? ResponseEntity.ok(nhaCungCapDTO) : ResponseEntity.notFound().build();
    }
}


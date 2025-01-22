package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.KichThuocDTO;
import com.example.datn_be.service.sanpham.KichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kich-thuoc")
@CrossOrigin("*")
public class KichThuocController {

    private final KichThuocService kichThuocService;

    @Autowired
    public KichThuocController(KichThuocService kichThuocService) {
        this.kichThuocService = kichThuocService;
    }

    @GetMapping("/search-e")
    public ResponseEntity<Page<KichThuocDTO>> searchKichThuocsEn(@RequestParam(defaultValue = "0" ) int page,
                                                            @RequestParam(defaultValue = "5") int size,
                                                            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(kichThuocService.searchKichThuocPageByKeyWordWithTrangThaiTrue(keyword, page, size));
    }

    @GetMapping("/search-d")
    public ResponseEntity<Page<KichThuocDTO>> searchKichThuocsDis(@RequestParam(defaultValue = "0" ) int page,
                                                              @RequestParam(defaultValue = "5") int size,
                                                              @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(kichThuocService.searchKichThuocPageByKeyWordWithTrangThaiFalse(keyword, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<KichThuocDTO>> searchKichThuocs(@RequestParam(defaultValue = "0" ) int page,
                                                                  @RequestParam(defaultValue = "5") int size,
                                                                  @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(kichThuocService.searchKichThuocPageByKeyWord(keyword, page, size));
    }

    @GetMapping("/list/all")
    public ResponseEntity<Page<KichThuocDTO>> getKichThuocs(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(kichThuocService.getKichThuocDTOPage(page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<KichThuocDTO>> getKichThuocDisables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(kichThuocService.getKichThuocDTOPageWithTrangThaiFalse(page, size));
    }

    @GetMapping("/list/enables")
    public ResponseEntity<Page<KichThuocDTO>> getKichThuocEnables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(kichThuocService.getKichThuocDTOPageWithTrangThaiTrue(page, size));
    }

    @PostMapping("/add")
    public ResponseEntity<KichThuocDTO> addKichThuoc(@RequestBody KichThuocDTO kichThuocDTO) {
        KichThuocDTO savedKichThuoc = kichThuocService.addKichThuoc(kichThuocDTO);
        return ResponseEntity.ok(savedKichThuoc);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<KichThuocDTO> updateKichThuoc(@PathVariable int id, @RequestBody KichThuocDTO kichThuocDTO) {
        KichThuocDTO updatedKichThuoc = kichThuocService.updateKichThuoc(id, kichThuocDTO);
        if (updatedKichThuoc != null) {
            return ResponseEntity.ok(updatedKichThuoc);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<KichThuocDTO> getKichThuocById(@PathVariable int id) {
        KichThuocDTO kichThuocDTO = kichThuocService.getKichThuocById(id);
        return kichThuocDTO != null ? ResponseEntity.ok(kichThuocDTO) : ResponseEntity.notFound().build();
    }
}


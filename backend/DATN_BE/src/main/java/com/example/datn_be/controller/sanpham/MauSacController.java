package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.MauSacDTO;
import com.example.datn_be.service.sanpham.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mau-sac")
@CrossOrigin("*")
public class MauSacController {

    private final MauSacService mauSacService;

    @Autowired
    public MauSacController(MauSacService mauSacService) {
        this.mauSacService = mauSacService;
    }


    @GetMapping("/search-e")
    public ResponseEntity<Page<MauSacDTO>> searchMauSacsEn(@RequestParam(defaultValue = "0" ) int page,
                                                           @RequestParam(defaultValue = "5") int size,
                                                           @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(mauSacService.searchMauSacPageByKeyWordWithTrangThaiTrue(keyword, page, size));
    }

    @GetMapping("/search-d")
    public ResponseEntity<Page<MauSacDTO>> searchMauSacsDis(@RequestParam(defaultValue = "0" ) int page,
                                                           @RequestParam(defaultValue = "5") int size,
                                                           @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(mauSacService.searchMauSacPageByKeyWordWithTrangThaiFalse(keyword, page, size));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<MauSacDTO>> searchMauSacs(@RequestParam(defaultValue = "0" ) int page,
                                                            @RequestParam(defaultValue = "5") int size,
                                                            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(mauSacService.searchMauSacPageByKeyWord(keyword, page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<MauSacDTO>> getMauSacDisables(@RequestParam(defaultValue = "0" ) int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(mauSacService.getMauSacDTOPageWithTrangThaiFalse(page, size));
    }

    @GetMapping("/list/enables")
    public ResponseEntity<Page<MauSacDTO>> getMauSaEnables(@RequestParam(defaultValue = "0" ) int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(mauSacService.getMauSacDTOPageWithTrangThaiTrue(page, size));
    }
    @GetMapping("/list/all")
    public ResponseEntity<Page<MauSacDTO>> getMauSacs(@RequestParam(defaultValue = "0" ) int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(mauSacService.getMauSacDTOPage(page, size));
    }

    @PostMapping("/add")
    public ResponseEntity<MauSacDTO> addMauSac(@RequestBody MauSacDTO mauSacDTO) {
        MauSacDTO savedMauSac = mauSacService.addMauSac(mauSacDTO);
        return ResponseEntity.ok(savedMauSac);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MauSacDTO> updateMauSac(@PathVariable int id, @RequestBody MauSacDTO mauSacDTO) {
        MauSacDTO updatedMauSac = mauSacService.updateMauSac(id, mauSacDTO);
        if (updatedMauSac != null) {
            return ResponseEntity.ok(updatedMauSac);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<MauSacDTO> getMauSacById(@PathVariable int id) {
        MauSacDTO mauSacDTO = mauSacService.getMauSacById(id);
        return mauSacDTO != null ? ResponseEntity.ok(mauSacDTO) : ResponseEntity.notFound().build();
    }
}


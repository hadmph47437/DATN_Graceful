package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.TaAoDTO;
import com.example.datn_be.service.sanpham.TaAoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ta-ao")
@CrossOrigin("*")
public class TaAoController {

    private final TaAoService taAoService;

    @Autowired
    public TaAoController(TaAoService taAoService) {
        this.taAoService = taAoService;
    }

    @GetMapping("/list/all")
    public ResponseEntity<Page<TaAoDTO>> getTaAoPage(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(taAoService.getTaAoDTOPage(page, size));
    }

    @GetMapping("/list/enables")
    public ResponseEntity<Page<TaAoDTO>> getTaAoPageEnables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(taAoService.getTaAoDTOPageWithTrangThaiTrue(page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<TaAoDTO>> getTaAoDisables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(taAoService.getTaAoDTOPageWithTrangThaiFalse(page, size));
    }

    @GetMapping("/search-e")
    public ResponseEntity<Page<TaAoDTO>> searchTaAoDisables(@RequestParam(defaultValue = "0" ) int page,
                                                         @RequestParam(defaultValue = "5") int size,
                                                         @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(taAoService.searchTaAoByKeyWordWithTrangThaiTrue(keyword, page, size));
    }

    @GetMapping("/search-d")
    public ResponseEntity<Page<TaAoDTO>> searchTaAoEnables(@RequestParam(defaultValue = "0" ) int page,
                                                         @RequestParam(defaultValue = "5") int size,
                                                         @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(taAoService.searchTaAoByKeyWordWithTrangThaiFalse(keyword, page, size));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<TaAoDTO>> searchTaAos(@RequestParam(defaultValue = "0" ) int page,
                                                           @RequestParam(defaultValue = "5") int size,
                                                           @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(taAoService.searchTaAoByKeyWord(keyword, page, size));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<TaAoDTO> getTaAoById(@PathVariable int id) {
        TaAoDTO taAoDTO = taAoService.getTaAoById(id);
        if (taAoDTO != null) {
            return ResponseEntity.ok(taAoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<TaAoDTO> addTaAo(@RequestBody TaAoDTO taAoDTO) {
        TaAoDTO savedTaAoDTO = taAoService.addTaAo(taAoDTO);
        return ResponseEntity.ok(savedTaAoDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaAoDTO> updateTaAo(@PathVariable int id, @RequestBody TaAoDTO taAoDTO) {
        TaAoDTO updatedTaAoDTO = taAoService.updateTaAo(id, taAoDTO);
        if (updatedTaAoDTO != null) {
            return ResponseEntity.ok(updatedTaAoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

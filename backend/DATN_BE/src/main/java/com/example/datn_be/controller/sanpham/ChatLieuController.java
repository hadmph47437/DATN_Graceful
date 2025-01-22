package com.example.datn_be.controller.sanpham;

import com.example.datn_be.dto.sanpham.ChatLieuDTO;
import com.example.datn_be.service.sanpham.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat-lieu")
@CrossOrigin("*")
public class ChatLieuController {

    private final ChatLieuService chatLieuService;

    @Autowired
    public ChatLieuController(ChatLieuService chatLieuService) {
        this.chatLieuService = chatLieuService;
    }


    @GetMapping("/search-e")
    public ResponseEntity<Page<ChatLieuDTO>> searchChatLieusEn(@RequestParam(defaultValue = "0" ) int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(required = false, defaultValue = "") String keyword) {
        return ResponseEntity.ok(chatLieuService.searchChatLieuPageByKeywordWithTrangThaiTrue(page, size, keyword));
    }

    @GetMapping("/search-d")
    public ResponseEntity<Page<ChatLieuDTO>> searchChatLieusDis(@RequestParam(defaultValue = "0" ) int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(required = false, defaultValue = "") String keyword) {
        return ResponseEntity.ok(chatLieuService.searchChatLieuPageByKeywordWithTrangThaiFalse(page, size, keyword));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ChatLieuDTO>> searchChatLieus(@RequestParam(defaultValue = "0" ) int page,
                                                                @RequestParam(defaultValue = "5") int size,
                                                                @RequestParam(required = false, defaultValue = "") String keyword) {
        return ResponseEntity.ok(chatLieuService.searchChatLieuPageByTen(page, size, keyword));
    }

    @GetMapping("/list/all")
    public ResponseEntity<Page<ChatLieuDTO>> getChatLieuAll(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(chatLieuService.getChatLieuDTOPage(page, size));
    }

    @GetMapping("/list/disables")
    public ResponseEntity<Page<ChatLieuDTO>> getChatLieuDisables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(chatLieuService.getChatLieuDTOPageSWithTrangThaiFalse(page, size));
    }

    @GetMapping("/list/enables")
    public ResponseEntity<Page<ChatLieuDTO>> getChatLieuEnables(@RequestParam(defaultValue = "0" ) int page, @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(chatLieuService.getChatLieuDTOPageWithTrangThaiTrue(page, size));
    }

    @PostMapping("/add")
    public ResponseEntity<ChatLieuDTO> addChatLieu(@RequestBody ChatLieuDTO chatLieuDTO) {
        ChatLieuDTO savedChatLieu = chatLieuService.addChatLieu(chatLieuDTO);
        return ResponseEntity.ok(savedChatLieu);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ChatLieuDTO> updateChatLieu(@PathVariable int id, @RequestBody ChatLieuDTO chatLieuDTO) {
        ChatLieuDTO updatedChatLieu = chatLieuService.updateChatLieu(id, chatLieuDTO);
        if (updatedChatLieu != null) {
            return ResponseEntity.ok(updatedChatLieu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ChatLieuDTO> getChatLieuById(@PathVariable int id) {
        ChatLieuDTO chatLieuDTO = chatLieuService.getChatLieuById(id);
        return chatLieuDTO != null ? ResponseEntity.ok(chatLieuDTO) : ResponseEntity.notFound().build();
    }
}

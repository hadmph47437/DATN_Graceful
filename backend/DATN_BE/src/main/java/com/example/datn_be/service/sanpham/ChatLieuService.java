package com.example.datn_be.service.sanpham;

import com.example.datn_be.dto.sanpham.ChatLieuDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChatLieuService {

    Page<ChatLieuDTO> getChatLieuDTOPage(int page, int size);

    Page<ChatLieuDTO> getChatLieuDTOPageWithTrangThaiTrue(int page, int size);

    Page<ChatLieuDTO> getChatLieuDTOPageSWithTrangThaiFalse(int page, int size);

    Page<ChatLieuDTO> searchChatLieuPageByKeywordWithTrangThaiTrue(int page, int size, String keyword);

    Page<ChatLieuDTO> searchChatLieuPageByTen(int page, int size, String ten);

    Page<ChatLieuDTO> searchChatLieuPageByKeywordWithTrangThaiFalse(int page, int size, String keyword);

    ChatLieuDTO addChatLieu(ChatLieuDTO chatLieuDTO);

    ChatLieuDTO updateChatLieu(int id, ChatLieuDTO chatLieuDTO);

    ChatLieuDTO getChatLieuById(int id);


}
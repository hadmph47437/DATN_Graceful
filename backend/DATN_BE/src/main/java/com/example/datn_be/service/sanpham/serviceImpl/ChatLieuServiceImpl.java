package com.example.datn_be.service.sanpham.serviceImpl;

import com.example.datn_be.dto.sanpham.ChatLieuDTO;
import com.example.datn_be.entiy.ChatLieu;
import com.example.datn_be.repository.ChatLieuRepository;
import com.example.datn_be.service.sanpham.ChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {

    @Autowired
    private ChatLieuRepository chatLieuRepository;

    public static ChatLieuDTO toDTO(ChatLieu chatLieu) {
        if (chatLieu == null) {
            return null;
        }
        return new ChatLieuDTO(
                chatLieu.getId(),
                chatLieu.getTen(),
                chatLieu.getMoTa(),
                chatLieu.getTrangThai(),
                chatLieu.getNgayTao()
        );
    }

    public static ChatLieu toEntity(ChatLieuDTO dto) {
        if (dto == null) {
            return null;
        }
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setId(dto.getId());
        chatLieu.setTen(dto.getTenChatLieu());
        chatLieu.setMoTa(dto.getMoTa());
        chatLieu.setTrangThai(dto.getTrangThai());
        chatLieu.setNgayTao(dto.getNgayTao());
        return chatLieu;
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
    public Page<ChatLieuDTO> getChatLieuDTOPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieu> chatLieuPage = chatLieuRepository.findAll(pageable);
        return chatLieuPage.map(ChatLieuServiceImpl::toDTO);
    }

    @Override
    public Page<ChatLieuDTO> getChatLieuDTOPageWithTrangThaiTrue(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieu> chatLieuPage = chatLieuRepository.findByTrangThaiTrue(pageable);
        return chatLieuPage.map(ChatLieuServiceImpl::toDTO);
    }

    @Override
    public Page<ChatLieuDTO> getChatLieuDTOPageSWithTrangThaiFalse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieu> chatLieuPage = chatLieuRepository.findByTrangThaiFalse(pageable);
        return chatLieuPage.map(ChatLieuServiceImpl::toDTO);
    }

    @Override
    public Page<ChatLieuDTO> searchChatLieuPageByKeywordWithTrangThaiTrue(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieu> chatLieuPage = chatLieuRepository.findChatLieusByTenContainingIgnoreCaseAndTrangThai(removeAccentAndToLower(keyword), true, pageable);
        return chatLieuPage.map(ChatLieuServiceImpl::toDTO);
    }

    @Override
    public Page<ChatLieuDTO> searchChatLieuPageByKeywordWithTrangThaiFalse(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieu> chatLieuPage = chatLieuRepository.findChatLieusByTenContainingIgnoreCaseAndTrangThai(removeAccentAndToLower(keyword), false, pageable);
        return chatLieuPage.map(ChatLieuServiceImpl::toDTO);
    }

    @Override
    public Page<ChatLieuDTO> searchChatLieuPageByTen(int page, int size, String ten) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieu> chatLieuPage = chatLieuRepository.findChatLieusByTenContainingIgnoreCase(removeAccentAndToLower(ten), pageable);
        return chatLieuPage.map(ChatLieuServiceImpl::toDTO);
    }

    @Override
    public ChatLieuDTO addChatLieu(ChatLieuDTO chatLieuDTO) {
        ChatLieu chatLieu = toEntity(chatLieuDTO);

        chatLieu.setNgayTao(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));

        ChatLieu savedChatLieu = chatLieuRepository.save(chatLieu);

        return toDTO(savedChatLieu);
    }

    @Override
    public ChatLieuDTO updateChatLieu(int id, ChatLieuDTO chatLieuDTO) {
        Optional<ChatLieu> existingChatLieuOptional = chatLieuRepository.findById(id);

        if (existingChatLieuOptional.isPresent()) {
            ChatLieu existingChatLieu = existingChatLieuOptional.get();

            existingChatLieu.setTen(chatLieuDTO.getTenChatLieu());
            existingChatLieu.setMoTa(chatLieuDTO.getMoTa());
            existingChatLieu.setTrangThai(chatLieuDTO.getTrangThai());

            ChatLieu updatedChatLieu = chatLieuRepository.save(existingChatLieu);

            return toDTO(updatedChatLieu);
        } else {
            return null;
        }
    }

    @Override
    public ChatLieuDTO getChatLieuById(int id) {
        Optional<ChatLieu> chatLieuOptional = chatLieuRepository.findById(id);
        if (chatLieuOptional.isPresent()) {
            ChatLieu chatLieu = chatLieuOptional.get();
            return toDTO(chatLieu);
        } else {
            return null;
        }
    }

}
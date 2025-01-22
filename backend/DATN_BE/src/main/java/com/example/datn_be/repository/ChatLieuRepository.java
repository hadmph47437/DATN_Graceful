package com.example.datn_be.repository;

import com.example.datn_be.entiy.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, Integer> {

    Page<ChatLieu> findByTrangThaiTrue(Pageable pageable);

    Page<ChatLieu> findByTrangThaiFalse(Pageable pageable);

    Page<ChatLieu> findChatLieusByTenContainingIgnoreCase(@Param("keyword") String ten,
                                                          Pageable pageable);

    Page<ChatLieu> findChatLieusByTenContainingIgnoreCaseAndTrangThai(@Param("keyword") String ten,
                                                                      @Param("trangThai") Boolean trangThai,
                                                                      Pageable pageable);

}

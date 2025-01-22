package com.example.datn_be.dto.sanpham;

import lombok.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO {
    private Integer id;

    private String maAoDai;

    private String tenAoDai;

    private String moTa;

    private Boolean trangThai;

    private ZonedDateTime ngayTao;

    private Integer loaiAoDaiId;

    private String tenLoaiAoDai;

    private Integer chatLieuId;

    private String tenChatLieu;

    private Integer taAoId;

    private String tenTaAo;

    private Integer nhaCungCapId;

    private String tenNhaCungCap;

    private List<String> anhList;
}

package com.example.datn_be.dto.sanpham;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiSanPhamDTO {
    private Integer id;

    private String tenLoaiAoDai;

    private String moTa;

    private Boolean trangThai;

    private ZonedDateTime ngayTao;
}


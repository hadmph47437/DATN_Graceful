package com.example.datn_be.dto.sanpham;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCapDTO {
    private Integer id;

    private String tenNhaCungCap;

    private String diaChi;

    private String soDienThoai;

    private String email;

    private Boolean trangThai;

    private String moTa;

    private ZonedDateTime ngayTao;
}


package com.example.datn_be.dto.sanpham;

import lombok.*;

import java.time.ZonedDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KichThuocDTO {
    private Integer id;

    private String tenKichThuoc;

    private String moTa;

    private Boolean trangThai;

    private ZonedDateTime ngayTao;
}


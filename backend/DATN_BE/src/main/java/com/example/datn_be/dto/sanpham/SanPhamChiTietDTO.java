package com.example.datn_be.dto.sanpham;

import lombok.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTietDTO {
    private Integer id;

    private String maAoDaiChiTiet;

    private Double giaGoc;

    private Double giaBan;

    private Integer soLuong;

    private Boolean trangThai;

    private String anhUrl;

    private ZonedDateTime ngayTao;

    private Integer aoDaiId;

    private String maAoDai;

    private String tenAoDai;

    private Integer mauSacId;

    private String tenMauSac;

    private Integer kichThuocId;

    private String tenKichThuoc;

}


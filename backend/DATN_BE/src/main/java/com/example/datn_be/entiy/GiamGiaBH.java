package com.example.datn_be.entiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "giam_gia")
public class GiamGiaBH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma_giam_gia")
    private String maGiamGia;

    @Column(name = "ten_giam_gia")
    private String tenGiamGia;

    @Column(name = "phan_tram_giam_gia")
    private Integer phanTramGiamGia;

    @Column(name = "tien_giam_gia")
    private BigDecimal tienGiamGia;

    @Column(name = "toi_da_giam_gia")
    private BigDecimal toiDaGiamGia;

    @Column(name = "gia_tri_toi_thieu")
    private BigDecimal giaTriToiThieu;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}


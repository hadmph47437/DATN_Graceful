package com.example.datn_be.entiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma_khuyen_mai",insertable = false, updatable = false)
    private String maKhuyenMai;

    @Column(name = "ten_khuyen_mai")
    private String tenKhuyenMai;

    @Column(name = "phan_tram_giam")
    private Integer phanTramGiam;

    @ManyToOne
    @JoinColumn(name = "id_ao_dai_chi_tiet", referencedColumnName = "id")
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ngay_tao", updatable = false)
    private LocalDateTime ngayTao;;
}

package com.example.datn_be.entiy;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ao_dai")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma_ao_dai", insertable = false, updatable = false, unique = true)
    private String maAoDai;

    @Column(name = "ten_ao_dai", unique = true)
    private String tenAoDai;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ngay_tao")
    private ZonedDateTime ngayTao;

    @ManyToOne
    @JoinColumn(name = "id_loai_ao_dai", referencedColumnName = "id")
    private LoaiSanPham loaiAoDai;

    @ManyToOne
    @JoinColumn(name = "id_chat_lieu", referencedColumnName = "id")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "id_ta_ao", referencedColumnName = "id")
    private TaAo taAo;

    @ManyToOne
    @JoinColumn(name = "id_nha_cung_cap", referencedColumnName = "id")
    private NhaCungCap nhaCungCap;

    @OneToMany(mappedBy = "aoDai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anh> anhList;
}

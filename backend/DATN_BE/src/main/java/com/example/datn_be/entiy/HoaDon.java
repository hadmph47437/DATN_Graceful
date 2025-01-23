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
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name = "phuong_thuc_thanh_toan")
    private Boolean phuongThucThanhToan;

    @Column(name = "tong_tien", precision = 18, scale = 0)
    private BigDecimal tongTien;

    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String ghiChu;

    @Column(name = "phi_giao_hang", precision = 18, scale = 0)
    private BigDecimal phiGiaoHang;

    @Column(name = "hinh_thuc_mua_hang")
    private Boolean hinhThucMuaHang;

    @Column(name = "dia_chi_giao_hang", columnDefinition = "TEXT")
    private String diaChiGiaoHang;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "trang_thai")
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHangBH khachHang;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NhanVienBH nhanVien;

    @ManyToOne
    @JoinColumn(name = "id_giam_gia")
    private GiamGiaBH giamGia;
}


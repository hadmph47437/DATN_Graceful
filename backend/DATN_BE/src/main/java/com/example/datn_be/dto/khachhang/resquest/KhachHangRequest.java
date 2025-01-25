package com.example.datn_be.dto.khachhang.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangRequest {

    private Integer id;

    private String maKhachHang;

    private String hoTen;

    private Boolean gioiTinh;

    private LocalDate ngaySinh;

    private String email;

    private String soDienThoai;

    private String tenDangNhap;

    private LocalDate ngayTao;

    private List<DiaChiEditRequest> diaChis;
}

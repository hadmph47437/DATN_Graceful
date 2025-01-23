package com.example.datn_be.dto.nhanvien;

import com.example.datn_be.entiy.vaitro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienRequest {
    private Integer id;

    private String maNhanVien;

    private String tenDangNhap;

    private String hoVaTen;

    private Integer gioiTinh;

    private String ngaySinh;

    private String email;

    private String matKhau;

    private String soDienThoai;

    private String diaChi;

    private Boolean trangThai;

    private vaitro vaiTro;

}

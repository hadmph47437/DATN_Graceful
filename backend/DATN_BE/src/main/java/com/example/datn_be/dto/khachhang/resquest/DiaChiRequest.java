package com.example.datn_be.dto.khachhang.resquest;

import com.example.datn_be.entiy.KhachHang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaChiRequest {

    private String duong;

    private String quan;

    private String thanhPho;

    private String tinh;

    private Boolean macDinh;

    private KhachHang khachHang;
}

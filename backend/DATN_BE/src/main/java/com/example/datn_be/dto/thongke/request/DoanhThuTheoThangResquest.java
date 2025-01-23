package com.example.datn_be.dto.thongke.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoanhThuTheoThangResquest {

    private Integer thang;

    private Integer nam;

    private Integer tongSoHoaDon;

    private Double tongDoanhThuThang;

    private Double tongPhiShip;

    private Double tongDoanhThuThucTe;

}

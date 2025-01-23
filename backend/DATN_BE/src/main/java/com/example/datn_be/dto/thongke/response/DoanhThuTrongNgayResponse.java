package com.example.datn_be.dto.thongke.response;

import java.time.LocalDate;

public interface DoanhThuTrongNgayResponse {

    LocalDate getNgay();

    Integer getSoHoaDon();

    Double getTongDoanhThu();

    Double getTongPhiShip();

    Double getDoanhThuThucTe();

}

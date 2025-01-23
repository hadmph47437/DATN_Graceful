package com.example.datn_be.dto.thongke.response;

import java.time.LocalDate;

public interface DoanhThuTheoKhoangThoiGianRes {

    LocalDate getNgay();

    Integer getTongHoaDon();

    Double getTongDoanhThu();

    Double getTongPhiShip();

    Double getTongGiamGia();

    Double getDoanhThuThucTe();
}

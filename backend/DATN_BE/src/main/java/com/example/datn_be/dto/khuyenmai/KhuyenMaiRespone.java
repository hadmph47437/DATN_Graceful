package com.example.datn_be.dto.khuyenmai;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class KhuyenMaiRespone {
        private String tenKhuyenMai;

        private Integer phanTramGiam;

        private String maAoDaiChiTiet;

        private LocalDate ngayBatDau;

        private LocalDate ngayKetThuc;

        private Boolean trangThai;

        private String moTa;

        private LocalDateTime ngayTao;


}

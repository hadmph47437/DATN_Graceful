package com.example.datn_be.dto.khuyenmai;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SanPhamChiTietKhuyenMaiDTO {
    private Integer id;

    private String maAoDaiChiTiet;

    private Double giaGoc;

    private Integer soLuong;

    private Boolean trangThai;

    private LocalDateTime ngayTao;

    private String maAoDai;

    private String tenAoDai;

    private String tenMauSac;

    private String tenKichThuoc;

    private String tenloaiAoDai;

    private String tenChatLieu;

    private String tenTaAo;
}

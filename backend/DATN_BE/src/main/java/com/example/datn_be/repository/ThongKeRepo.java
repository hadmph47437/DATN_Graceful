package com.example.datn_be.repository;

import com.example.datn_be.dto.thongke.response.DoanhThuTheoKhoangThoiGianRes;
import com.example.datn_be.dto.thongke.response.DoanhThuTheoNamResponse;
import com.example.datn_be.dto.thongke.response.DoanhThuTheoThangResponse;
import com.example.datn_be.dto.thongke.response.DoanhThuTrongNgayResponse;
import com.example.datn_be.dto.thongke.response.KetQuaThongKeTop5Response;
import com.example.datn_be.dto.thongke.response.LoaiAoDaiBanNhieuResponse;
import com.example.datn_be.entiy.AoDai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ThongKeRepo extends JpaRepository<AoDai, Integer> {
    @Query(value = """
                    SELECT TOP 5
                        ad.ma_ao_dai as maAoDai,
                    	ad.ten_ao_dai as tenAoDai,
                        SUM(hdct.so_luong) as tongSoLuongDaBan,
                        SUM(hdct.so_luong * hdct.don_gia) as tongTienDaBan
                    FROM ao_dai ad
                    JOIN ao_dai_chi_tiet adct ON ad.id = adct.id_ao_dai
                    JOIN hoa_don_chi_tiet hdct ON adct.id = hdct.id_ao_dai_chi_tiet
                    JOIN hoa_don hd ON hdct.id_hoa_don = hd.id
                    WHERE hd.trang_thai = N'Đã thanh toán'
                    GROUP BY ad.ma_ao_dai, ad.ten_ao_dai
                    ORDER BY tongSoLuongDaBan DESC
            """, nativeQuery = true)
    List<KetQuaThongKeTop5Response> getTop5AoDaiBanChay();

    @Query(value = """
                   SELECT
                       CAST(hd.ngay_tao AS DATE) as ngay,
                       COUNT(DISTINCT hd.id) as so_hoa_don,
                       SUM(hd.tong_tien) as tong_doanh_thu,
                       SUM(hd.phi_giao_hang) as tong_phi_ship,
                       SUM(hd.tong_tien- hd.phi_giao_hang) as doanh_thu_thuc_te
                   FROM hoa_don hd
                   WHERE hd.trang_thai = N'Đã thanh toán'
                   AND CAST(hd.ngay_tao AS DATE) = :ngay
                   GROUP BY CAST(hd.ngay_tao AS DATE)
            """, nativeQuery = true)
    List<DoanhThuTrongNgayResponse> getDoanhThuTrongNgay(LocalDate ngay);

    @Query(value = """
                SELECT
                    DAY(hd.ngay_tao) as ngay,
                    COUNT(DISTINCT hd.id) as tongSoHoaDon,
                    CAST(SUM(hd.tong_tien) AS DECIMAL(20,2)) as tongDoanhThuNgay,
                    CAST(SUM(hd.phi_giao_hang) AS DECIMAL(20,2)) as tongPhiShip,
                    CAST(SUM(hd.tong_tien - hd.phi_giao_hang) AS DECIMAL(20,2)) as tongDoanhThuThucTe
                FROM hoa_don hd
                WHERE hd.trang_thai = N'Đã thanh toán'
                    AND MONTH(hd.ngay_tao) = :thang
                    AND YEAR(hd.ngay_tao) = :nam
                GROUP BY DAY(hd.ngay_tao), MONTH(hd.ngay_tao), YEAR(hd.ngay_tao)
                ORDER BY DAY(hd.ngay_tao) ASC
            """, nativeQuery = true)
    List<DoanhThuTheoThangResponse> getDoanhThuTheoThang(Integer thang, Integer nam);

    @Query(value = """
                SELECT
                    MONTH(hd.ngay_tao) as thang,
                    COUNT(DISTINCT hd.id) as tongSoHoaDon,
                    CAST(SUM(hd.tong_tien) AS DECIMAL(20,2)) as tongDoanhThuThang,
                    CAST(SUM(hd.phi_giao_hang) AS DECIMAL(20,2)) as tongPhiShip,
                    CAST(SUM(hd.tong_tien - hd.phi_giao_hang) AS DECIMAL(20,2)) as DoanhThuThucTe
                FROM hoa_don hd
                WHERE hd.trang_thai = N'Đã thanh toán'
                    AND YEAR(hd.ngay_tao) = :nam
                GROUP BY MONTH(hd.ngay_tao)
                ORDER BY thang ASC
            """, nativeQuery = true)
    List<DoanhThuTheoNamResponse> getDoanhThuTheoNam(Integer nam);


    @Query(value = """
                     SELECT
                            CAST(hd.ngay_tao AS DATE) as ngay,
                            COUNT(DISTINCT hd.id) as tongHoaDon,
                            SUM(hd.tong_tien) as tongDoanhThu,
                            SUM(hd.phi_giao_hang) as tongPhiShip,
                            SUM(gg.tien_giam_gia) as tongGiamGia,
                            SUM(hd.tong_tien - COALESCE(gg.tien_giam_gia, 0)) as doanhThuThucTe
                        FROM hoa_don hd
                        LEFT JOIN giam_gia gg ON hd.id_giam_gia = gg.id
                        WHERE hd.trang_thai = N'Đã thanh toán'
                        AND hd.ngay_tao BETWEEN :startDate AND :endDate
                        GROUP BY CAST(hd.ngay_tao AS DATE)
                        ORDER BY ngay DESC
            """, nativeQuery = true)
    List<DoanhThuTheoKhoangThoiGianRes> getDoanhThuTheoKhoangThoiGian(LocalDate startDate, LocalDate endDate);

    @Query(value = """
            SELECT
                lad.ten_loai_ao_dai as tenLoaiAoDai,
                COUNT(hdct.so_luong) as soLuong,
                CAST(COUNT(hdct.so_luong) * 100.0 / SUM(COUNT(hdct.so_luong)) OVER() AS DECIMAL(10,2)) as phanTram
            FROM loai_ao_dai lad
            JOIN ao_dai ad ON lad.id = ad.id_loai_ao_dai
            JOIN ao_dai_chi_tiet adct ON ad.id = adct.id_ao_dai
            JOIN hoa_don_chi_tiet hdct ON adct.id = hdct.id_ao_dai_chi_tiet
            GROUP BY lad.ten_loai_ao_dai
            ORDER BY COUNT(hdct.so_luong) DESC
            """, nativeQuery = true)
    List<LoaiAoDaiBanNhieuResponse> getLoaiAoDaiBanNhieu();
}

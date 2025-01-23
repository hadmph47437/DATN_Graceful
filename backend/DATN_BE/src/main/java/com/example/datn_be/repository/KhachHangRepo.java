package com.example.datn_be.repository;

import com.example.datn_be.dto.khachhang.response.KhachHangResponse;
import com.example.datn_be.entiy.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang, Integer> {
    @Query(value = """
                SELECT DISTINCT
                         kh.id,
                         kh.ma_khach_hang as maKhachHang,
                         kh.ho_ten as hoTen,
                         kh.gioi_tinh as gioiTinh,
                         kh.ngay_sinh as ngaySinh,
                         kh.email as email,
                         kh.so_dien_thoai as soDienThoai,
                         kh.ten_dang_nhap as tenDangNhap,
                         kh.mat_khau as matKhau,
                         kh.ngay_tao as ngayTao,
                         (
                             SELECT STRING_AGG(
                                 CONCAT(dc.duong, ', ', dc.quan, ', ', dc.thanh_pho, ', ', dc.tinh), '; '
                             )
                             FROM dia_chi dc
                             WHERE dc.id_khach_hang = kh.id
                         ) as diaChis
                FROM khach_hang kh
            """, nativeQuery = true)
    List<KhachHangResponse> getAllKhachHang();


    @Query(value = """
                    SELECT
                          kh.id,
                          kh.ma_khach_hang as maKhachHang,
                          kh.ho_ten as hoTen,
                          kh.gioi_tinh as gioiTinh,
                          kh.ngay_sinh as ngaySinh,
                          kh.email as email,
                          kh.so_dien_thoai as soDienThoai,
                          kh.ten_dang_nhap as tenDangNhap,
                          kh.mat_khau as matKhau,
                          kh.ngay_tao as ngayTao,
                          (
                             SELECT STRING_AGG(
                                CONCAT(dc.duong, ', ', dc.quan, ', ', dc.thanh_pho, ', ', dc.tinh), '; '
                                      )
                                FROM dia_chi dc
                                WHERE dc.id_khach_hang = kh.id
                            ) as diaChis
                    FROM khach_hang kh
                    WHERE kh.ho_ten LIKE %:hoTen%
                    OR kh.email LIKE %:email%
                    OR kh.so_dien_thoai LIKE %:sdt%
                    OR kh.ten_dang_nhap LIKE %:tenDangNhap
            """, nativeQuery = true)
    List<KhachHangResponse> timKiemNangCao(String hoTen, String email, String sdt, String tenDangNhap);


}

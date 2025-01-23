package com.example.datn_be.repository;

import com.example.datn_be.entiy.nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepo extends JpaRepository<nhanvien,Integer> {

    //    @Query("""
//
//            SELECT nv
//FROM nhanvien nv
//WHERE nv.hoVaTen LIKE %:ho_va_ten%
//
//
//""")
//    List<nhanvien> timkiem(@Param("ho_va_ten") String ten);
//    }
    @Query("""
    SELECT new map(
           nv.id AS id, 
           nv.maNhanVien AS maNhanVien, 
           nv.tenDangNhap AS tenDangNhap, 
           nv.hoVaTen AS hoVaTen, 

            CASE\s
               WHEN nv.gioiTinh = 1 THEN 'Nam'
               WHEN nv.gioiTinh = 0 THEN 'Nữ'
               ELSE 'Khác'
           END AS gioiTinh,
           nv.ngaySinh AS ngaySinh, 
           nv.email AS email, 
           nv.soDienThoai AS soDienThoai, 
           nv.diaChi AS diaChi, 
           CASE\s
                                                     WHEN nv.trangThai = true THEN 'Hoạt động'\s
                                                     ELSE 'Không hoạt động'\s
                                                 END AS trangThai,
           nv.vaiTro.tenVaiTro AS chucvu
    )
    FROM nhanvien nv
""")
       List<Object[]> getAllNhanVien();
    @Query("""
        SELECT nv
        FROM nhanvien nv
        WHERE 
            (:keyword IS NULL OR nv.hoVaTen LIKE %:keyword% 
            OR nv.tenDangNhap LIKE %:keyword%
            OR nv.ngaySinh LIKE %:keyword%
            OR nv.maNhanVien LIKE %:keyword%
            OR nv.diaChi LIKE %:keyword%
            OR nv.email LIKE %:keyword%)
""")
    List<nhanvien> timkiem(@Param("keyword") String keyword);
@Query("""
    SELECT 
        nv.maNhanVien,
        nv.hoVaTen,
        nv.ngaySinh,
        nv.email,
        nv.soDienThoai,
        nv.diaChi,
        nv.tenDangNhap,
        nv.vaiTro.tenVaiTro
    FROM nhanvien nv
""")
List<Object[]> getAllNhanVienaccount();


}
//    @Query("""
//SELECT nv
//FROM nhanvien nv
//WHERE
//    (:hoVaTen IS NULL OR nv.hoVaTen LIKE %:hoVaTen%) AND
//    (:tenDangNhap IS NULL OR nv.tenDangNhap LIKE %:tenDangNhap%) AND
//    (:email IS NULL OR nv.email LIKE %:email%)
//""")
//    List<nhanvien> timKiem(@Param("hoVaTen") String hoVaTen,
//                           @Param("tenDangNhap") String tenDangNhap,
//                           @Param("email") String email);
//
//
//
//}
//public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
//    List<NhanVien> findByHoVaTenContainingOrTenDangNhapContainingOrEmailContaining(String hoVaTen, String tenDangNhap, String email);
//}




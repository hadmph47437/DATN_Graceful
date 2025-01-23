package com.example.datn_be.repository;

import com.example.datn_be.dto.khachhang.response.DiaChiResponse;
import com.example.datn_be.entiy.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiaChiRepo extends JpaRepository<DiaChi, Integer> {
    @Query(value = """
        select 
            dc.duong as duong,
            dc.quan as quan,
            dc.thanh_pho as thanhPho,
            dc.tinh as tinh,
            dc.mac_dinh as macDinh,
            kh.ho_ten as hoTenKhachHang,
            kh.ten_dang_nhap as tenDangNhapKhachHang
        from dia_chi dc join khach_hang kh on dc.id_khach_hang= kh.id
""",nativeQuery = true)
    List<DiaChiResponse> getAllDiaChi();
}

package com.example.datn_be.service.khachhang;

import com.example.datn_be.dto.khachhang.resquest.KhachHangRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface KhachHangService {
    ResponseEntity<?> getAllKhachHang();

    ResponseEntity<?> addKhachHang(KhachHangRequest khachHangRequest);

    ResponseEntity<?> updateKhachHang(Integer id, KhachHangRequest khachHangRequest);

    ResponseEntity<?> getKhachHangById(Integer id);

    ResponseEntity<?> phanTrang(Pageable pageable);

    ResponseEntity<?> timKiem(String hoTen, String email, String sdt, String tenDangNhap);

}

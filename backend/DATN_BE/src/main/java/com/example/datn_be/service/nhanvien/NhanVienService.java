package com.example.datn_be.service.nhanvien;

import com.example.datn_be.dto.nhanvien.NhanVienRequest;
import org.springframework.http.ResponseEntity;

public interface NhanVienService {
    ResponseEntity<?> getAllNhanVien();

    ResponseEntity<?> addNhanVien(NhanVienRequest nhanVienRequest);

    ResponseEntity<?> updateNhanVien(Integer id, NhanVienRequest nhanVienRequest);
    ResponseEntity<?> getNhanVienById(Integer id);
}

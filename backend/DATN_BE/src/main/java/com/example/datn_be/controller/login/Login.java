package com.example.datn_be.controller.login;

import com.example.datn_be.entiy.NhanVienBH;
import com.example.datn_be.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/auth")
public class Login {

    @Autowired
    private NhanVienRepository nhanVienRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody NhanVienBH loginRequest) {
        // Tìm nhân viên dựa trên tên đăng nhập và mật khẩu
        NhanVienBH nhanVien = nhanVienRepo.findByTenDangNhapAndMatKhau(
                loginRequest.getTenDangNhap(),
                loginRequest.getMatKhau()
        );

        if (nhanVien == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Tên đăng nhập hoặc mật khẩu không chính xác");
        }

        // Chuẩn bị dữ liệu trả về
        Map<String, Object> response = new HashMap<>();
        response.put("hoVaTen", nhanVien.getHoVaTen());
        response.put("vaiTro", nhanVien.getVaiTro().getTen()); // Lấy tên vai trò từ VaiTroDN
        return ResponseEntity.ok(response);
    }
}


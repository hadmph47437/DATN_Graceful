package com.example.datn_be.service.khachhang.impl;

import com.example.datn_be.dto.khachhang.response.KhachHangResponse;
import com.example.datn_be.dto.khachhang.resquest.DiaChiEditRequest;
import com.example.datn_be.dto.khachhang.resquest.KhachHangRequest;
import com.example.datn_be.entiy.DiaChi;
import com.example.datn_be.entiy.KhachHang;
import com.example.datn_be.repository.KhachHangRepo;
import com.example.datn_be.service.khachhang.KhachHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KhachHangServieImpl implements KhachHangService {

    @Autowired
    private final KhachHangRepo khachHangRepo;

    @Override
    public ResponseEntity<?> getAllKhachHang() {
        return new ResponseEntity<>(khachHangRepo.getAllKhachHang(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addKhachHang(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        BeanUtils.copyProperties(khachHangRequest, khachHang);

        if (khachHangRequest.getDiaChis() != null && !khachHangRequest.getDiaChis().isEmpty()) {
            List<DiaChi> diaChis = new ArrayList<>();
            for (DiaChiEditRequest diaChiRequest : khachHangRequest.getDiaChis()) {
                DiaChi diaChi = new DiaChi();
                diaChi.setDuong(diaChiRequest.getDuong());
                diaChi.setQuan(diaChiRequest.getQuan());
                diaChi.setThanhPho(diaChiRequest.getThanhPho());
                diaChi.setTinh(diaChiRequest.getTinh());
                diaChi.setMacDinh(diaChiRequest.getMacDinh());
                diaChi.setKhachHang(khachHang);
                diaChis.add(diaChi);
            }
            khachHang.setDiaChis(diaChis);
        }
        return ResponseEntity.ok(khachHangRepo.save(khachHang));
    }

    @Override
    public ResponseEntity<?> updateKhachHang(Integer id, KhachHangRequest khachHangRequest) {
        KhachHang khachHang = khachHangRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + id));
        khachHang.setHoTen(khachHangRequest.getHoTen());
        khachHang.setGioiTinh(khachHangRequest.getGioiTinh());
        khachHang.setNgaySinh(khachHangRequest.getNgaySinh());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setSoDienThoai(khachHangRequest.getSoDienThoai());
        khachHang.setTenDangNhap(khachHangRequest.getTenDangNhap());
        khachHang.setMatKhau(khachHangRequest.getMatKhau());
        khachHang.setNgayTao(khachHangRequest.getNgayTao());
        if (khachHangRequest.getDiaChis() != null && !khachHangRequest.getDiaChis().isEmpty()) {
            List<DiaChi> diaChis = new ArrayList<>();
            for (DiaChiEditRequest diaChiRequest : khachHangRequest.getDiaChis()) {
                DiaChi diaChi = new DiaChi();
                diaChi.setDuong(diaChiRequest.getDuong());
                diaChi.setQuan(diaChiRequest.getQuan());
                diaChi.setThanhPho(diaChiRequest.getThanhPho());
                diaChi.setTinh(diaChiRequest.getTinh());
                diaChi.setMacDinh(diaChiRequest.getMacDinh());
                diaChi.setKhachHang(khachHang);
                diaChis.add(diaChi);
            }
            khachHang.setDiaChis(diaChis);
        }
        return ResponseEntity.ok(khachHangRepo.save(khachHang));
    }

    @Override
    public ResponseEntity<?> getKhachHangById(Integer id) {
        return khachHangRepo.findById(id)
                .map(khachHang -> {
                    return new ResponseEntity<>(khachHang, HttpStatus.OK);
                })
                .orElseThrow(() -> new NullPointerException("Khach Hang not found!"));
    }

    @Override
    public ResponseEntity<?> phanTrang(Pageable pageable) {
        return new ResponseEntity<>(khachHangRepo.findAll(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> timKiem(@RequestParam String hoTen, @RequestParam String email,
                                     @RequestParam String sdt, @RequestParam String tenDangNhap) {
        return new ResponseEntity<>(khachHangRepo.timKiemNangCao(hoTen, email, sdt, tenDangNhap), HttpStatus.OK);
    }
}

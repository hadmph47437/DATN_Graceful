package com.example.datn_be.service.khachhang.impl;

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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KhachHangServieImpl implements KhachHangService {

    @Autowired
    private final KhachHangRepo khachHangRepo;

    @Override
    public ResponseEntity<?> getAllKhachHang(String keyword, Pageable pageable) {
        return new ResponseEntity<>(khachHangRepo.getAllKhachHang(keyword, pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addKhachHang(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        BeanUtils.copyProperties(khachHangRequest, khachHang);
        if (khachHangRequest.getDiaChis() != null && !khachHangRequest.getDiaChis().isEmpty()) {
            List<DiaChi> diaChis = khachHangRequest.getDiaChis().stream()
                    .map(diaChiRequest -> {
                        DiaChi diaChi = new DiaChi();
                        diaChi.setDuong(diaChiRequest.getDuong());
                        diaChi.setQuan(diaChiRequest.getQuan());
                        diaChi.setThanhPho(diaChiRequest.getThanhPho());
                        diaChi.setTinh(diaChiRequest.getTinh());
                        diaChi.setMacDinh(diaChiRequest.getMacDinh());
                        diaChi.setKhachHang(khachHang);
                        return diaChi;
                    })
                    .collect(Collectors.toList());

            khachHang.setDiaChis(diaChis);
        }
        return new ResponseEntity<>(khachHangRepo.save(khachHang), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateKhachHang(Integer id, KhachHangRequest khachHangRequest) {
        KhachHang khachHang = khachHangRepo.findById(khachHangRequest.getId())
                .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại"));

        khachHang.setHoTen(khachHangRequest.getHoTen());
        khachHang.setGioiTinh(khachHangRequest.getGioiTinh());
        khachHang.setNgaySinh(khachHangRequest.getNgaySinh());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setSoDienThoai(khachHangRequest.getSoDienThoai());

        List<DiaChi> diaChisMoi = khachHangRequest.getDiaChis().stream()
                .map(diaChiRequest -> {
                    DiaChi diaChi = new DiaChi();
                    diaChi.setDuong(diaChiRequest.getDuong());
                    diaChi.setQuan(diaChiRequest.getQuan());
                    diaChi.setThanhPho(diaChiRequest.getThanhPho());
                    diaChi.setTinh(diaChiRequest.getTinh());
                    diaChi.setMacDinh(diaChiRequest.getMacDinh());
                    diaChi.setKhachHang(khachHang);
                    return diaChi;
                })
                .collect(Collectors.toList());

        khachHang.getDiaChis().clear();

        khachHang.getDiaChis().addAll(diaChisMoi);
        return new ResponseEntity<>(khachHangRepo.save(khachHang), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getKhachHangById(Integer id) {
        return khachHangRepo.findById(id)
                .map(khachHang -> {
                    return new ResponseEntity<>(khachHang, HttpStatus.OK);
                })
                .orElseThrow(() -> new NullPointerException("Khach Hang not found!!"));
    }
}

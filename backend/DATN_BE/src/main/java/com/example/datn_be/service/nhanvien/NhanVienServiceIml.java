package com.example.datn_be.service.nhanvien;

import com.example.datn_be.dto.nhanvien.NhanVienRequest;
import com.example.datn_be.entiy.nhanvien;
import com.example.datn_be.repository.NhanVienRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NhanVienServiceIml implements NhanVienService {
@Autowired
private final NhanVienRepo nhanVienRepo;

    public NhanVienServiceIml(NhanVienRepo nhanVienRepo) {
        this.nhanVienRepo = nhanVienRepo;
    }

    @Override
    public ResponseEntity<?> getAllNhanVien() {
        return new ResponseEntity<>(nhanVienRepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addNhanVien(NhanVienRequest nhanVienRequest) {
        nhanvien nv=new nhanvien();
        BeanUtils.copyProperties(nhanVienRequest, nv);
        return new ResponseEntity<>(nhanVienRepo.save(nv), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateNhanVien(Integer id, NhanVienRequest nhanVienRequest) {
        return nhanVienRepo.findById(id)
                .map(nhanvien -> {
                    nhanvien.setMaNhanVien(nhanVienRequest.getMaNhanVien());
                    nhanvien.setTenDangNhap(nhanVienRequest.getTenDangNhap());
                    nhanvien.setHoVaTen(nhanVienRequest.getHoVaTen());
                    nhanvien.setGioiTinh(nhanVienRequest.getGioiTinh());
                    nhanvien.setNgaySinh(nhanVienRequest.getNgaySinh());
                    nhanvien.setEmail(nhanVienRequest.getEmail());
                    nhanvien.setSoDienThoai(nhanVienRequest.getSoDienThoai());
                    nhanvien.setDiaChi(nhanVienRequest.getDiaChi());
                    return new ResponseEntity<>(nhanVienRepo.save(nhanvien), HttpStatus.OK);

                })
                .orElseThrow(() -> new NullPointerException("nhân viên not found!"));
    }

//    @Override
//    public ResponseEntity<?> getNhanVienById(Integer id) {
//        return nhanVienRepo.findById(id)
//                .map(nhanvien -> {
//                    return new ResponseEntity<>(nhanvien, HttpStatus.OK);
//
//                })
//    }

    @Override
    public ResponseEntity<?> getNhanVienById(Integer id) {
        return nhanVienRepo.findById(id)
                .map(nhanvien -> {
                    return new ResponseEntity<>(nhanvien, HttpStatus.OK);
                })
                .orElseThrow(() -> new NullPointerException("nhân viên not found!"));
    }
}

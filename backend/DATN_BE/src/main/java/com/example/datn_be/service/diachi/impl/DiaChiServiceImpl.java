package com.example.datn_be.service.diachi.impl;

import com.example.datn_be.dto.khachhang.resquest.DiaChiRequest;
import com.example.datn_be.entiy.DiaChi;
import com.example.datn_be.repository.DiaChiRepo;
import com.example.datn_be.service.diachi.DiaChiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    private final DiaChiRepo diaChiRepo;

    @Override
    public ResponseEntity<?> getAllDiaChi() {
        return new ResponseEntity<>(diaChiRepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addDiaChi(DiaChiRequest diaChiRequest) {
        DiaChi dc= new DiaChi();
        BeanUtils.copyProperties(diaChiRequest,dc);
        return new ResponseEntity<>(diaChiRepo.save(dc), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateDiaChi(Integer id, DiaChiRequest diaChiRequest) {
        return diaChiRepo.findById(id)
                .map(diaChi -> {
                    diaChi.setDuong(diaChiRequest.getDuong());
                    diaChi.setQuan(diaChiRequest.getQuan());
                    diaChi.setThanhPho(diaChiRequest.getThanhPho());
                    diaChi.setTinh(diaChiRequest.getTinh());
                    diaChi.setMacDinh(diaChiRequest.getMacDinh());
                    return new ResponseEntity<>(diaChiRepo.save(diaChi), HttpStatus.OK);
                })
                .orElseThrow(()-> new NullPointerException("Dia Chi not found!"));
    }

    @Override
    public ResponseEntity<?> getDiaChiById(Integer id) {
        return diaChiRepo.findById(id)
                .map(diaChi -> {
                    return new ResponseEntity<>(diaChi, HttpStatus.OK);
                })
                .orElseThrow(()-> new NullPointerException("Dia Chi not found!"));
    }

    @Override
    public ResponseEntity<?> phanTrangDiaChi(Pageable pageable) {
        return new ResponseEntity<>(diaChiRepo.findAll(pageable), HttpStatus.OK);
    }
}

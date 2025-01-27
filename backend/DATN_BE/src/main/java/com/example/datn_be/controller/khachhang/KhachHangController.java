package com.example.datn_be.controller.khachhang;

import com.example.datn_be.dto.khachhang.resquest.KhachHangRequest;
import com.example.datn_be.service.khachhang.impl.KhachHangServieImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/khach-hang")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class KhachHangController {

    @Autowired
    private final KhachHangServieImpl khachHangServie;

    @GetMapping("hien-thi")
    public ResponseEntity<?> hienThi(@RequestParam(required = false) String keyword,
                                     @RequestParam(value = "page",defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return khachHangServie.getAllKhachHang(keyword,pageable);
    }

    @PostMapping("add")
    public ResponseEntity<?> addKhachHang(@RequestBody KhachHangRequest khachHangRequest){
        return khachHangServie.addKhachHang(khachHangRequest);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateKhachHang(@PathVariable Integer id, @RequestBody KhachHangRequest khachHangRequest){
        return khachHangServie.updateKhachHang(id, khachHangRequest);
    }

    @GetMapping("details/{id}")
    public ResponseEntity<?> detailsKhachHang(@PathVariable Integer id){
        return khachHangServie.getKhachHangById(id);
    }

}

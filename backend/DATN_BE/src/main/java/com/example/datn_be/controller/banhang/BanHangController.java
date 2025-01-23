package com.example.datn_be.controller.banhang;

import com.example.datn_be.entiy.AoDaiChiTietBH;
import com.example.datn_be.repository.AoDaiChiTietBHRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ban-hang")
public class BanHangController {

    @Autowired
    AoDaiChiTietBHRepo aoDaiChiTietBHRepo;

    @GetMapping("/ao-dai-chi-tiet/hien-thi/phan-trang")
    public Page<AoDaiChiTietBH> hienThiAoDaiChiTietPhanTrang(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(required = false) String keyWord)
    {
        Pageable pageable = PageRequest.of(page, size);
        if(keyWord != null && !keyWord.isEmpty()){
            return aoDaiChiTietBHRepo.timKiemSanPhamTheoKeyWord(keyWord,pageable);
        }else{
            return aoDaiChiTietBHRepo.findAll(pageable);
        }

    }
}

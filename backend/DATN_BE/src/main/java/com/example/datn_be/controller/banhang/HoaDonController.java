package com.example.datn_be.controller.banhang;

import com.example.datn_be.entiy.HoaDon;
import com.example.datn_be.entiy.HoaDonChiTiet;
import com.example.datn_be.repository.HoaDonChiTietRepo;
import com.example.datn_be.repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    HoaDonRepo hoaDonRepo;


    @Autowired
    HoaDonChiTietRepo hoaDonChiTietRepo;


    @GetMapping("/hien-thi-phan-trang")
    public Page<HoaDon> hienThiHoaDonPhanTrang(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size,
                                               @RequestParam(required = false) String searchQuery,
                                               @RequestParam(required = false) String trangThai) {
        Pageable pageable = PageRequest.of(page, size);
        if (searchQuery != null && !searchQuery.isEmpty()) {
            return hoaDonRepo.timKiemHoaDon(searchQuery, pageable);
        } else if (trangThai != null && !trangThai.isEmpty()) {
            return hoaDonRepo.findByTrangThai(trangThai, pageable);
        } else {
            return hoaDonRepo.findAll(pageable);
        }
    }

    // hien thi spct trong hd
    @GetMapping("/chi-tiet")
    public List<HoaDonChiTiet> getHoaDonChiTietByHoaDonId(@RequestParam("idHoaDon") Integer idHoaDon) {
        return hoaDonChiTietRepo.findByHoaDonId(idHoaDon);
    }

    @GetMapping("/tim-kiem-phan-trang")
    public Page<HoaDon> timKiemHoaDonPhanTrang(@RequestParam(required = false) String searchQuery,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hoaDonRepo.timKiemHoaDon(searchQuery, pageable);
    }


    @GetMapping("/tim-kiem-theo-trang-thai-phan-trang")
    public Page<HoaDon> timKiemHoaDonTheoTrangThaiPhanTrang(@RequestParam("trangThai") String trangThai,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hoaDonRepo.findByTrangThai(trangThai, pageable);
    }

}


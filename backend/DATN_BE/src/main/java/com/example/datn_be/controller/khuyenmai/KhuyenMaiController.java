package com.example.datn_be.controller.khuyenmai;
import com.example.datn_be.dto.khuyenmai.KhuyenMaiDTO;
import com.example.datn_be.dto.khuyenmai.SanPhamChiTietKhuyenMaiDTO;
import com.example.datn_be.entiy.KhuyenMai;
import com.example.datn_be.entiy.SanPhamChiTiet;
import com.example.datn_be.repository.KhuyenMaiRepository;
import com.example.datn_be.repository.SanPhamChiTietRepository;
import com.example.datn_be.dto.khuyenmai.KhuyenMaiRespone;
import com.example.datn_be.service.khuyenmai.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/khuyen-mai")
public class KhuyenMaiController {
    @Autowired
    KhuyenMaiService khuyenMaiService;
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @GetMapping("/hien-thi")

    public List<KhuyenMai> getAllGiamGia(){
        return khuyenMaiService.getAll();
    }
    @GetMapping("/hien-thi-san-phamct-dto")
    public List<SanPhamChiTietKhuyenMaiDTO> getListSPCT(){
        return khuyenMaiService.getListSPCT();
    }
    @GetMapping("/hien-thi-respone")
    public List<KhuyenMaiRespone> getAllGiam(){
        return khuyenMaiRepository.getAllRespone();
    }

@PostMapping("/them")
public ResponseEntity<String> themKhuyenMai(@RequestBody KhuyenMaiDTO khuyenMaiDTO) {
    try {
        // Tạo đối tượng KhuyenMai từ DTO
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setTenKhuyenMai(khuyenMaiDTO.getTenKhuyenMai());
        khuyenMai.setPhanTramGiam(khuyenMaiDTO.getPhanTramGiam());
        khuyenMai.setNgayBatDau(khuyenMaiDTO.getNgayBatDau());
        khuyenMai.setNgayKetThuc(khuyenMaiDTO.getNgayKetThuc());
        khuyenMai.setTrangThai(khuyenMaiDTO.getTrangThai());
        khuyenMai.setNgayTao(LocalDateTime.now());
        khuyenMai.setMoTa(khuyenMaiDTO.getMoTa());

        // Lấy danh sách sản phẩm chi tiết từ danh sách ID
        List<SanPhamChiTiet> sanPhamChiTietList = khuyenMaiDTO.getAoDaiChiTietIds().stream()
                .map(id -> sanPhamChiTietRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết với ID: " + id)))
                .toList();

        // Lưu từng sản phẩm chi tiết với khuyến mãi
        for (SanPhamChiTiet spct : sanPhamChiTietList) {
            KhuyenMai km = new KhuyenMai();
            km.setTenKhuyenMai(khuyenMaiDTO.getTenKhuyenMai());
            km.setPhanTramGiam(khuyenMaiDTO.getPhanTramGiam());
            km.setNgayBatDau(khuyenMaiDTO.getNgayBatDau());
            km.setNgayKetThuc(khuyenMaiDTO.getNgayKetThuc());
            km.setTrangThai(khuyenMaiDTO.getTrangThai());
            km.setNgayTao(LocalDateTime.now());
            km.setMoTa(khuyenMaiDTO.getMoTa());
            km.setSanPhamChiTiet(spct);
            khuyenMaiRepository.save(km);
        }

        return ResponseEntity.ok("Thêm khuyến mãi thành công!");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thêm khuyến mãi thất bại: " + e.getMessage());
    }
}

    @PutMapping("/cap-nhat")
    public String update(@RequestBody KhuyenMai khuyenMai) {
        if(khuyenMai == null){
            return "cập nhật thất bại";
        }
        khuyenMaiService.capNhat(khuyenMai);
        return "cập nhật thành công";
    }

    @DeleteMapping("delete-khuyen-mai/{id}")
    public String delete(@PathVariable("id") Integer id){
        khuyenMaiService.xoa(id);
        return "xóa thành công";
    }


    //    @GetMapping("phan-trang")
//    public List<KhuyenMai> phanTrang(@RequestParam(value = "page" , defaultValue = "0") Integer page){
//        int pageSize = 3;
//        Pageable pageable = PageRequest.of(page,pageSize);
//        return khuyenMaiRepository.findAll(pageable).getContent();
//    }

//    @PostMapping("/them")
//    public ResponseEntity<String> add(@RequestBody KhuyenMai khuyenMai) {
//        if (khuyenMai == null) {
//            return ResponseEntity.badRequest().body("Thêm thất bại: Dữ liệu không hợp lệ");
//        }
//        khuyenMaiService.them(khuyenMai);
//        return ResponseEntity.ok("Thêm thành công");
//    }
//    @PostMapping("/them")
//    public ResponseEntity<String> add(@RequestBody KhuyenMaiDTO khuyenMaiDTO) {
//        if (khuyenMaiDTO == null || khuyenMaiDTO.getAoDaiChiTietIds() == null) {
//            return ResponseEntity.badRequest().body("Thêm thất bại: Dữ liệu không hợp lệ");
//        }
//
//        // Thêm KhuyenMai
//        KhuyenMai khuyenMai = new KhuyenMai();
//        khuyenMai.setTenKhuyenMai(khuyenMaiDTO.getTenKhuyenMai());
//        khuyenMai.setPhanTramGiam(khuyenMaiDTO.getPhanTramGiam());
//        khuyenMai.setNgayBatDau(khuyenMaiDTO.getNgayBatDau());
//        khuyenMai.setNgayKetThuc(khuyenMaiDTO.getNgayKetThuc());
//        khuyenMai.setTrangThai(khuyenMaiDTO.getTrangThai());
//        khuyenMai.setNgayTao(LocalDateTime.now());
//        khuyenMai.setMoTa(khuyenMaiDTO.getMoTa());
//
//        khuyenMaiService.them(khuyenMai);
//
//        // Gắn các id_ao_dai_chi_tiet vào bản ghi KhuyenMai
//        for (Integer aoDaiChiTietId : khuyenMaiDTO.getAoDaiChiTietIds()) {
//            khuyenMai.setIdAoDaiChiTiet(aoDaiChiTietId);
//            khuyenMaiService.them(khuyenMai);
//        }
//
//        return ResponseEntity.ok("Thêm thành công");
//    }
}

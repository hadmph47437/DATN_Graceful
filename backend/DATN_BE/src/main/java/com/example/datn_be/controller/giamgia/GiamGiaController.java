package com.example.datn_be.controller.giamgia;

import com.example.datn_be.entiy.GiamGia;
import com.example.datn_be.repository.GiamGiaRepository;
import com.example.datn_be.service.giamgia.GiamGiaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GiamGiaController {
    @Autowired
    GiamGiaService giamGiaService;
    @Autowired
    GiamGiaRepository giamGiaRepository;
    @GetMapping("/giam-gia/hien-thi")
    public List<GiamGia> getAllGiamGia(){
        return giamGiaService.getAll();
    }

    @GetMapping("phan-trang")
    public List<GiamGia> phanTrang(@RequestParam(value = "page" , defaultValue = "0") Integer page){
        int pageSize = 3;
        Pageable pageable = PageRequest.of(page,pageSize);
        return giamGiaRepository.findAll(pageable).getContent();
    }

    @PostMapping("/giam-gia/them")
    public ResponseEntity<String> add(@RequestBody GiamGia giamGia) {
        if (giamGia == null) {
            return ResponseEntity.badRequest().body("Thêm thất bại: Dữ liệu không hợp lệ");
        }
        giamGiaService.them(giamGia);
        return ResponseEntity.ok("Thêm thành công");
    }




    //    @PostMapping("add-respone")
//    public String add(@RequestBody GiamGia giamGiaRequest) {
//        GiamGia giamGia = new GiamGia();
//        BeanUtils.copyProperties(GiamGiaRequest,giamGia);
//
//        if(giamGia == null){
//            return "thêm thất bại";
//        }
//        giamGiaService.them(giamGia);
//        return "thêm thành công";
//    }
    @PutMapping("/giam-gia/cap-nhat")
    public String update(@RequestBody GiamGia giamGia) {
        if(giamGia == null){
            return "cập nhật thất bại";
        }
        giamGiaService.capNhat(giamGia);
        return "cập nhật thành công";
    }

    @DeleteMapping("delete-giam-gia/{id}")
    public String delete(@PathVariable("id") Integer id){
        giamGiaService.xoa(id);
        return "xóa thành công";
    }
}

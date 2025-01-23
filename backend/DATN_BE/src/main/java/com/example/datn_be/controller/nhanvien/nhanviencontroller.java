package com.example.datn_be.controller.nhanvien;

import com.example.datn_be.dto.nhanvien.NhanVienRequest;
import com.example.datn_be.entiy.nhanvien;
import com.example.datn_be.entiy.vaitro;
import com.example.datn_be.repository.NhanVienRepo;
import com.example.datn_be.repository.VaiTroRepo;
import com.example.datn_be.service.nhanvien.NhanVienServiceIml;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class nhanviencontroller {
  @Autowired
  private NhanVienRepo nhanvienRepo;
  @Autowired
  private VaiTroRepo vaiTroRepo;

  @Autowired
  private final NhanVienServiceIml nhanvienServiceIml;


    @GetMapping("/nhan-vien/hien-thi3")
    public ResponseEntity<?> getNhanvien23() {
        return nhanvienServiceIml.getAllNhanVien();

    }

//@GetMapping("/nhan-vien/hien-thi")
//    public  List<Object[]> getNhanvien() {
//    return nhanvienRepo.getAllNhanVien();
//}
    @GetMapping("/nhan-vien/hien-thi-account")
    public List<Object[]> getNhanvien2() {
        return nhanvienRepo.getAllNhanVienaccount();
    }

@GetMapping("/nhan-vien/show-vai-tro")
    public List<vaitro> getvaitro() {
    return vaiTroRepo.findAll();

}

    @PostMapping("/nhan-vien/add")
    public ResponseEntity<?> addNhanVien(@RequestBody NhanVienRequest nhanVienRequest){
        return nhanvienServiceIml.addNhanVien(nhanVienRequest);
    }
//@PostMapping("/nhan-vien/add")
//    public ResponseEntity<String> addNhanvien(@RequestBody nhanvien nhanvien) {
////    nhanvien nv=nhanvienRepo.save(nhanvien);
////    if (nv==null){
////        return "thêm lỗi";
////    }
////    return "thêm thành công";
//    nhanvienRepo.save(nhanvien);  // Lưu nhân viên vào cơ sở dữ liệu
//    return ResponseEntity.ok("Thêm nhân viên thành công");
//}
//    @GetMapping("/nhan-vien/update/{id}")
//    public Optional<nhanvien> update(@PathVariable("id") Integer id){
//        return nhanvienRepo.findById(id);
//    }
//    @PutMapping("/nhan-vien/update")
//    public String updateNhanvien(@RequestBody nhanvien nhanvien) {
//    nhanvien nv=nhanvienRepo.save(nhanvien);
//    if (nv==null){
//        return "update lỗi";
//    }
//    return "update thành công";
//}

    @PutMapping("/nhan-vien/update/{id}")
    public ResponseEntity<?> updateNhanVien(@PathVariable Integer id, @RequestBody NhanVienRequest nhanVienRequest){
        return nhanvienServiceIml.updateNhanVien(id, nhanVienRequest);
    }
    @DeleteMapping("/nhan-vien/delete/{id}")
    public String deleteNhanVien(@PathVariable Integer id) {
        // Tìm kiếm nhân viên theo id
        Optional<nhanvien> optionalNhanVien = nhanvienRepo.findById(id);
        if (optionalNhanVien.isPresent()) {
            nhanvien nhanVien = optionalNhanVien.get();
            // Chuyển trạng thái thành 0 (ngừng hoạt động)
            nhanVien.setTrangThai(false);
            nhanvienRepo.save(nhanVien); // Lưu lại trạng thái mới
            return "Xoá thành công (trạng thái đã chuyển sang 0)";
        }
        return "Không tìm thấy nhân viên với id: " + id;
    }

    @GetMapping("/phan-trang")
    public List<nhanvien> phantrang(@RequestParam(value = "page",defaultValue = "0") Integer page){
        int size=2;
        Pageable pageable= PageRequest.of(page,size);
        return nhanvienRepo.findAll(pageable).getContent();
    }
//    @GetMapping("/tim-kiem")
//    public List<nhanvien> timkiem(@RequestParam("ho_va_ten") String ten) {
//        return nhanvienRepo.timkiem(ten);
//    }
@GetMapping("/tim-kiem")
public List<nhanvien> timkiem(@RequestParam(value = "keyword", required = false) String keyword) {
    return nhanvienRepo.timkiem(keyword);



}
    @GetMapping("/nhan-vien/details/{id}")
    public ResponseEntity<?> detailsNhanVien(@PathVariable Integer id){
        return nhanvienServiceIml.getNhanVienById(id);
    }






}

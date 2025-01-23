package com.example.datn_be.service.giamgia;

import com.example.datn_be.entiy.GiamGia;
import com.example.datn_be.repository.GiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GiamGiaService {
    @Autowired
    GiamGiaRepository giamGiaRepository;

    public List<GiamGia> getAll(){
        return giamGiaRepository.findAll();
    }
   public void them(GiamGia giamGia){
        giamGia.setNgayTao(LocalDateTime.now());
    giamGiaRepository.save(giamGia);
   }
    public void capNhat(GiamGia giamGia){
        giamGiaRepository.save(giamGia);
    }
    public void xoa(Integer id){
        giamGiaRepository.deleteById(id);
    }
}

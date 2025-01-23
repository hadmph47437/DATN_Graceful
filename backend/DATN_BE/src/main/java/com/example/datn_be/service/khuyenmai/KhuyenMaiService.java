package com.example.datn_be.service.khuyenmai;
import com.example.datn_be.dto.khuyenmai.SanPhamChiTietKhuyenMaiDTO;
import com.example.datn_be.entiy.KhuyenMai;
import com.example.datn_be.entiy.SanPhamChiTiet;
import com.example.datn_be.repository.KhuyenMaiRepository;
import com.example.datn_be.repository.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class KhuyenMaiService {
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    public List<SanPhamChiTietKhuyenMaiDTO> getListSPCT(){
        return  sanPhamChiTietRepository.getListSPCT();
    }
    public List<KhuyenMai> getAll(){
        return khuyenMaiRepository.findAll();
    }

    public void them(KhuyenMai khuyenMai){
        khuyenMai.setNgayTao(LocalDateTime.now());
        khuyenMaiRepository.save(khuyenMai);
    }
    public void capNhat(KhuyenMai khuyenMai){
        khuyenMaiRepository.save(khuyenMai);;
    }
    public void xoa(Integer id){
        khuyenMaiRepository.deleteById(id);
    };



}

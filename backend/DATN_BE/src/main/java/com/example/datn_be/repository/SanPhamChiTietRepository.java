package com.example.datn_be.repository;
import com.example.datn_be.dto.khuyenmai.SanPhamChiTietKhuyenMaiDTO;
import com.example.datn_be.entiy.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    @Query("""
SELECT new com.example.datn_be.dto.khuyenmai.SanPhamChiTietKhuyenMaiDTO
            
    (
    spct.id,
    spct.maAoDaiChiTiet,
    spct.giaGoc,
    spct.soLuong,
    spct.trangThai,
    spct.ngayTao,
    spct.aoDai.maAoDai,
    spct.aoDai.tenAoDai,
    spct.mauSac.tenMauSac,
    spct.kichThuoc.ten,
    spct.aoDai.loaiAoDai.tenLoaiAoDai,
    spct.aoDai.chatLieu.ten,
    spct.aoDai.taAo.ten
    
    )FROM SanPhamChiTiet spct
        """)
    List<SanPhamChiTietKhuyenMaiDTO> getListSPCT();
}

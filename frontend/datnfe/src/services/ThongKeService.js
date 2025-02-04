import axios from "axios";
const BASE_URL = 'http://localhost:8080/api/admin/thong-ke';
class ThongKeService {
    getTop5SanPham(){
        return axios.get(`${BASE_URL}/hien-thi-top-5`)
    }

    getDoanhThuTrongNgay(ngay){
        return axios.get(`${BASE_URL}/hien-thi-doanh-thu-theo-ngay`,{
            params:{ngay}
        })
    }

    getDoanhThuTheoThang(thang, nam){
        return axios.get(`${BASE_URL}/hien-thi-doanh-thu-theo-thang`,{
            params:{thang,nam}
        })
    }

    getDoanhThuTheoNam(nam){
        return axios.get(`${BASE_URL}/hien-thi-doanh-thu-theo-nam`,{
            params:{nam}
        })
    }

    getDoanhThuTheoKhoangThoiGian(startDate,endDate){
        return axios.get(`${BASE_URL}/hien-thi-doanh-thu-theo-khoang-thoi-gian`,{
            params:{startDate,endDate}
        })
    }

    getLoaiAoDaiBanNhieu(){
        return axios.get(`${BASE_URL}/hien-thi-loai-ao-dai-ban-nhieu`)
    }
}
export default new ThongKeService();
import axios from "axios";
 const getAllKhachHangAPI= 'http://localhost:8080/api/admin/khach-hang/hien-thi'
 const getAllDiaChisAPI= 'http://localhost:8080/api/admin/dia-chi/hien-thi'
 class KhachHangService {
    getAllKhachHang(params) {
        return axios.get(getAllKhachHangAPI,{
            params:{
                maKhachHang: params.maKhachHang || '',
                hoTen: params.hoTen || '',
                email: params.email || '',
                soDienThoai: params.soDienThoai || '',
                tenDangNhap: params.tenDangNhap || '',
                page: params.page || 0,
                size: params.size || 5
            }
        });
    }
    getAllDiaChi(){
        return axios.get(getAllDiaChisAPI)
    }
 }

 export default new KhachHangService();
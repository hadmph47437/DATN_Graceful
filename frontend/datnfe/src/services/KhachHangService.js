import axios from "axios";
 const getAllKhachHangAPI= 'http://localhost:8080/api/admin/khach-hang/hien-thi'

 class KhachHangService {
    getAllKhachHang() {
        return axios.get(getAllKhachHangAPI);
    }
 }

 export default new KhachHangService();
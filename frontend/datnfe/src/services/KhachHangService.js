import axios from "axios";
const getAllKhachHangAPI =
  "http://localhost:8080/api/admin/khach-hang/hien-thi";
const getAllDiaChisAPI = "http://localhost:8080/api/admin/dia-chi/hien-thi";
const getKhachHangByIdAPI =
  "http://localhost:8080/api/admin/khach-hang/details";
const updateKhachHangAPI = "http://localhost:8080/api/admin/khach-hang/update";
const addKhachHangAPI = "http://localhost:8080/api/admin/khach-hang/add";
class KhachHangService {
  getAllKhachHang(params) {
    return axios.get(getAllKhachHangAPI, {
      params: {
        keyword: params.keyword || "",
        page: params.page || 0,
        size: params.size || 5,
      },
    });
  }
  getAllDiaChi() {
    return axios.get(getAllDiaChisAPI);
  }

  getKhachHangById(id) {
    return axios.get(`${getKhachHangByIdAPI}/${id}`);
  }

  updateKhachHang(id, khachHang) {
    return axios.put(`${updateKhachHangAPI}/${id}`, khachHang);
  }

  addKhachHang(khachHang) {
    return axios.post(`${addKhachHangAPI}`, khachHang);
  }
}

export default new KhachHangService();

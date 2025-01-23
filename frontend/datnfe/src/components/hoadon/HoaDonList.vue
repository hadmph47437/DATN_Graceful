<template>
    <div class="container mt-4">
        <h4>Danh sách hóa đơn</h4>

        <div class="search-timkiem">
            <input type="text" v-model="searchQuery" placeholder="Nhập mã HD hoặc SDT KH" />
            <button @click="timKiemHoaDon">Tìm kiếm</button>
            
            <label for="trangThai">Trạng thái: </label>
            <select v-model="trangThai" @change="timKiemHoaDonTheoTrangThai">
                <option value="">Chọn trạng thái</option>
                <option value="Đã thanh toán">Đã thanh toán</option>
                <option value="Chưa thanh toán">Chưa thanh toán</option>
            </select>

                <button class="xuat-excel">
                    <i class="fas fa-file-excel"></i> Xuất Excel
                </button>

        </div>

        <div class="table-container">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Mã HD</th>
                        <th>Mã NV</th>  
                        <th>Tên NV</th>                      
                        <th>Tên KH</th>
                        <th>SDT KH</th>
                        <th>Địa Chỉ Giao</th>
                        <th>Phí Ship</th>
                        <th>Hình Thức</th>
                        <th>Giảm Giá</th>
                        <th>Ghi chú</th>
                        <th>Phương thức thanh toán</th>
                        <th>Ngày Tạo</th>
                        <th>Tổng Tiền</th>
                        <th>Trạng Thái</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="hoaDon in hoaDons" :key="hoaDon.id" @click="hienThiHoaDonChiTiet(hoaDon.id)">
                        <td>{{ hoaDon.maHoaDon }}</td>
                        <td>{{ hoaDon.nhanVien.maNhanVien }}</td>
                        <td>{{ hoaDon.nhanVien.hoVaTen }}</td>
                        <td>{{ hoaDon.khachHang.hoTen }}</td>
                        <td>{{ hoaDon.khachHang.soDienThoai }}</td>
                        <td>{{ hoaDon.diaChiGiaoHang }}</td>
                        <td>{{ hoaDon.phiGiaoHang }}</td>
                        <td>{{ hoaDon.hinhThucMuaHang ? 'Tại quầy' : 'Online' }}</td>
                        <td>{{ hoaDon.giamGia.maGiamGia }}</td>
                         <td>{{ hoaDon.ghiChu }}</td>
                        <td>{{ hoaDon.phuongThucThanhToan ? 'Chuyển khoản' : 'Tiền mặt' }}</td>       
                        <td>{{ new Date(hoaDon.ngayTao).toLocaleString() }}</td>
                        <td>{{ hoaDon.tongTien }}</td>                  
                        <td>{{ hoaDon.trangThai }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
            <div class="phanTrang">
    <button :disabled="page === 0" @click="diToiTrangDau">First</button>
    <button :disabled="page === 0" @click="diToiTrangTruoc">Previ</button>
    <div class="phanTrang-info">
    <span>{{ page + 1 }} / {{ totalPages }}</span>
</div>
    <button :disabled="page >= totalPages - 1" @click="diToiTrangSau">Next</button>
    <button :disabled="page >= totalPages - 1" @click="diToiTrangCuoi">Last</button>
</div>

        <h5>Sản phẩm trong hóa đơn</h5>
        <div v-if="hoaDonChiTiets.length > 0" class="table-container-hoa-don-chi-tiet">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Mã HD</th>
                        <th>Mã HDCT</th>
                        <th>Mã Áo dài CT</th>
                        <th>Tên áo dài</th>
                        <th>Màu sắc</th>
                        <th>Kích thước</th>
                        <th>Chất liệu</th>
                        <th>Số Lượng</th>
                        <th>Đơn Giá</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="hdct in hoaDonChiTiets" :key="hdct.id">
                        <td>{{ hdct.hoaDon.maHoaDon }}</td>
                        <td>{{ hdct.maHDCT }}</td>
                        <td>{{ hdct.aoDaiChiTiet.maAoDaiChiTiet }}</td>
                        <td>{{ hdct.aoDaiChiTiet.aoDai.tenAoDai }}</td>
                        <td>{{ hdct.aoDaiChiTiet.mauSac.tenMauSac }}</td>
                        <td>{{ hdct.aoDaiChiTiet.kichThuoc.ten }}</td>
                        <td>{{ hdct.aoDaiChiTiet.aoDai.chatLieu.ten }}</td>
                        <td>{{ hdct.soLuong }}</td>
                        <td>{{ hdct.giaBan }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        
            <div v-else>
                <table class="table table-bordered table-hover">
             <thead><tr><th>Chọn 1 hóa đơn để xem</th></tr></thead></table>
            </div>
        </div>
</template>

<script>

import axios from 'axios';

export default {
    data() {
        return {
            hoaDons: [],
            hoaDonChiTiets: [],
            searchQuery: '',
            trangThai: '',
            page: 0,
            size: 5,
            totalPages: 0,
        };
    },
    created() {
        this.hienThiHoaDon();
    },
    methods: {

    hienThiHoaDon() {
    axios.get('http://localhost:8080/hoa-don/hien-thi-phan-trang', {
        params: {
            page: this.page,         
            size: this.size,          
            searchQuery: this.searchQuery,  
            trangThai: this.trangThai   
        }
    })
    .then(response => {
        this.hoaDons = response.data.content;
        this.totalPages = response.data.totalPages;
    })
    .catch(error => console.error(error));
},

        hienThiHoaDonChiTiet(idHoaDon) {
    axios
        .get('http://localhost:8080/hoa-don/chi-tiet', { params: { idHoaDon } })
        .then(response => {
            this.hoaDonChiTiets = response.data; 
        })
        .catch(error => {
            console.error('Lỗi khi hiển thị chi tiết hóa đơn:', error);
        });
},

timKiemHoaDon() {
    this.page = 0; 
    this.hienThiHoaDon(); 
    axios
        .get('http://localhost:8080/hoa-don/tim-kiem-phan-trang', {
            params: {
                searchQuery: this.searchQuery,
                page: this.page,               
                size: this.size             
            }
        })
        .then(response => {
            if (response.data.content.length === 0) {
                this.hoaDons = [];
                this.totalPages = 0;
            } else {
                this.hoaDons = response.data.content;
                this.totalPages = response.data.totalPages;
            }
            this.hoaDonChiTiets = [];
        })
        .catch(error => console.error(error));
},


timKiemHoaDonTheoTrangThai() {
    this.page = 0;
    this.hienThiHoaDon(); 
    axios
        .get('http://localhost:8080/hoa-don/tim-kiem-theo-trang-thai-phan-trang', {
            params: {
                trangThai: this.trangThai,  // Truyền trạng thái chọn
                page: this.page,            // Trang hiện tại
                size: this.size             // Số lượng bản ghi mỗi trang
            }
        })
        .then(response => {
            this.hoaDons = response.data.content;
            this.totalPages = response.data.totalPages;
        })
        .catch(error => console.error(error));
        this.hoaDonChiTiets = [];
},


diToiTrangDau() {
    this.page = 0;
    this.hienThiHoaDon(); 
},

diToiTrangTruoc() {
    if (this.page > 0) {
        this.page -= 1;
        this.hienThiHoaDon(); 
    }
},

diToiTrangSau() {
    if (this.page < this.totalPages - 1) {
        this.page += 1;
        this.hienThiHoaDon();
    }
},

diToiTrangCuoi() {
    this.page = this.totalPages - 1;
    this.hienThiHoaDon();
},},};
</script>



















<style scoped>
.container {
    margin-left: 260px;
    max-width: 1260px;
    font-size: 13px;
}
.table-container {
    overflow-x: auto;
}
.table {
    width: 100%;
}
th, td {
    text-align: center;
}
.table-hover tbody tr:hover {
    background-color: #f1f1f1;
}
.search-timkiem {
align-items: center;
    display: flex;
    gap: 20px;
    margin-bottom: 8px;
}
.search-timkiem label {
    font-size: 14px;
    margin-right: 5px; /* Khoảng cách giữa label và select */
}
.search-timkiem input {
    padding: 4px;
    font-size: 14px;
    width: 180px;
}
.search-timkiem button {
    padding: 8px 12px;
    font-size: 14px;
    background-color: #007bff;
    color: #fff;
    border: none;
    cursor: pointer;
    border-radius: 12px;
    margin-right: 15px;
}
.search-timkiem button:hover {
    background-color: #0056b3;
}
.search-timkiem select {
    padding: 8px;
    font-size: 14px;
    border-radius: 8px; /* Tạo góc bo tròn cho select */
    border: 1px solid #ccc; /* Thêm viền cho select */
}
.xuat-excel {
    margin-left: 50px; /* Điều chỉnh giá trị này để thay đổi khoảng cách */
}
.phanTrang {
    display: flex;
    justify-content: center; /* Căn giữa các nút */
    gap: 8px; /* Khoảng cách giữa các nút */
    margin-top: 20px;
}
.phanTrang button {
    padding: 6px 12px; /* Giảm padding để nút nhỏ gọn hơn */
    font-size: 14px;
    background-color: transparent; /* Nền trong suốt */
    color: #007bff; /* Màu chữ xanh cho nút */
    border: 1px solid #007bff; /* Viền mảnh với màu xanh */
    border-radius: 4px; /* Bo góc nhẹ */
    cursor: pointer;
    transition: all 0.3s ease; /* Hiệu ứng chuyển động */
}
.phanTrang button:hover {
    background-color: #007bff; /* Nền chuyển sang màu xanh khi hover */
    color: #fff; /* Chữ trắng khi hover */
    border-color: #0056b3; /* Màu viền đậm hơn khi hover */
}
.phanTrang button:disabled {
    background-color: #f0f0f0; /* Nền xám khi vô hiệu hóa */
    color: #b0b0b0; /* Màu chữ xám */
    border-color: #d0d0d0; /* Viền xám khi vô hiệu hóa */
    cursor: not-allowed; /* Hiển thị con trỏ không cho phép */
}
.phanTrang-info {
    display: flex;
    align-items: center; /* Căn giữa theo chiều dọc */
    gap: 8px; /* Khoảng cách giữa các phần tử */
    font-size: 14px;
    color: #333; /* Màu chữ đậm cho dễ đọc */
    font-weight: bold;
}
.phanTrang-info span {
    padding: 6px 12px; /* Thêm padding cho phần hiển thị trang */
    border: 1px solid #007bff; /* Viền mảnh màu xanh */
    border-radius: 4px; /* Bo tròn các góc */
    background-color: #f9f9f9; /* Nền sáng */
    color: #007bff; /* Màu chữ xanh */
    transition: background-color 0.3s ease, color 0.3s ease; /* Hiệu ứng chuyển màu */
}
.phanTrang-info span:hover {
    background-color: #007bff; /* Nền màu xanh khi hover */
    color: #fff; /* Chữ trắng khi hover */
}

/* CSS riêng cho bảng "Sản phẩm trong hóa đơn" */
.table-container-hoa-don-chi-tiet {
    max-height: 150px; /* Điều chỉnh chiều cao để hiển thị thanh cuộn dọc */
    overflow-y: auto;
    display: block;
}

.table {
    width: 100%;
    border-collapse: collapse; /* Đảm bảo các cell không bị chồng lấn */
}

th, td {
    text-align: center;
    padding: 8px;
}

th {
    background-color: #f1f1f1; /* Màu nền cho tiêu đề */
    position: sticky; /* Đảm bảo tiêu đề cố định khi cuộn */
    top: 0; /* Đặt tiêu đề luôn ở trên cùng */
    z-index: 1; /* Đảm bảo tiêu đề luôn ở trên cùng các dòng */
}

.table-hover tbody tr:hover {
    background-color: #f1f1f1;
}

</style>

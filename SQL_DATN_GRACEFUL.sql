CREATE DATABASE DATN_GRACEFUL
GO
USE DATN_GRACEFUL
GO
-- Bảng Nhà cung cấp
CREATE TABLE nha_cung_cap (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten_nha_cung_cap NVARCHAR(255) NOT NULL,
    dia_chi NVARCHAR(255),
    so_dien_thoai NVARCHAR(20),
    email NVARCHAR(255),
	trang_thai BIT,
	mo_ta NVARCHAR(255),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Màu sắc
CREATE TABLE mau_sac (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten_mau_sac NVARCHAR(255) NOT NULL,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Kích thước
CREATE TABLE kich_thuoc (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(50) NOT NULL,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Chất liệu
CREATE TABLE chat_lieu (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255) NOT NULL,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Loại sản phẩm
CREATE TABLE loai_san_pham (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255) NOT NULL,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Tà áo
CREATE TABLE ta_ao (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255) NOT NULL,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Sản phẩm
CREATE TABLE san_pham (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_san_pham NVARCHAR(10) NOT NULL,
    ten_san_pham NVARCHAR(255) NOT NULL,
    mo_ta NVARCHAR(MAX),
    loai_san_pham_id INT,
    nha_cung_cap_id INT,
	ta_ao_id INT,
	chat_lieu_id INT,
	trang_thai BIT,
	ngay_tao DATETIME DEFAULT GETDATE()
    FOREIGN KEY (loai_san_pham_id) REFERENCES Loai_san_pham(id),
    FOREIGN KEY (nha_cung_cap_id) REFERENCES Nha_cung_cap(id),
	FOREIGN KEY (ta_ao_id) REFERENCES Ta_ao(id),
    FOREIGN KEY (chat_lieu_id) REFERENCES Chat_lieu(id)
);
GO
-- Bảng Sản phẩm chi tiết
CREATE TABLE san_pham_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_san_pham_chi_tiet NVARCHAR(10) NOT NULL,
    san_pham_id INT,
    mau_sac_id INT,
	gia_goc DECIMAL(18,0),
	gia_ban DECIMAL(18,0),
    kich_thuoc_id INT,
    so_luong INT NOT NULL,
	trang_thai BIT,
	ngay_tao DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (san_pham_id) REFERENCES San_pham(id),
    FOREIGN KEY (mau_sac_id) REFERENCES Mau_sac(id),
    FOREIGN KEY (kich_thuoc_id) REFERENCES Kich_thuoc(id),
);
GO
-- Bảng Ảnh
CREATE TABLE anh (
    id INT PRIMARY KEY IDENTITY(1,1),
    anh_url NVARCHAR(MAX),
    id_san_pham INT,
	ngay_tao DATETIME DEFAULT GETDATE(),
	trang_thai BIT
    FOREIGN KEY (id_san_pham) REFERENCES San_pham(id)
);
GO
-- Bảng Vai trò
CREATE TABLE vai_tro (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(50) NOT NULL
);
GO
-- Bảng Người dùng
CREATE TABLE nguoi_dung (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_nguoi_dung VARCHAR(10) NOT NULL,
	ten_dang_nhap VARCHAR(10) NOT NULL,
    ho_va_ten NVARCHAR(255) NOT NULL,
	gioi_tinh BIT NOT NULL,
	ngay_sinh DATE NOT NULL,
    email NVARCHAR(255),
    mat_khau NVARCHAR(255) NOT NULL,
    so_dien_thoai NVARCHAR(20),
	dia_chi NVARCHAR(MAX),
	trang_thai BIT,
    vai_tro_id INT,
	ngay_tao DATETIME DEFAULT GETDATE(),
	FOREIGN KEY (vai_tro_id) REFERENCES Vai_tro(id)
);
GO
-- Bảng Khách hàng
CREATE TABLE khach_hang (
    id INT PRIMARY KEY IDENTITY(1,1),
    ho_ten NVARCHAR(255) NOT NULL,
	gioi_tinh BIT NOT NULL,
	ngay_sinh DATE,
    email NVARCHAR(255),
    so_dien_thoai NVARCHAR(20),
	ten_dang_nhap VARCHAR(10) NOT NULL,
	mat_khau VARCHAR(50) NOT NULL,
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Địa chỉ
CREATE TABLE dia_chi (
    id INT PRIMARY KEY IDENTITY(1,1),
    duong NVARCHAR(100),
	quan NVARCHAR(100),
	thanh_pho NVARCHAR(100),
	tinh NVARCHAR(100),
	mac_dinh BIT,
    khach_hang_id INT
	FOREIGN KEY (khach_hang_id) REFERENCES Khach_hang(id)
);
GO
-- Bảng Giảm giá
CREATE TABLE giam_gia (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten_giam_gia NVARCHAR(255) NOT NULL,
    phan_tram_giam_gia INT,
	tien_giam_gia DECIMAL(18,0),
	toi_da_giam_gia DECIMAL(18,0),
	gia_tri_toi_thieu DECIMAL(18,0),
	so_luong INT NOT NULL,
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Hóa đơn
CREATE TABLE hoa_don (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_hoa_don VARCHAR(10) NOT NULL,
	phuong_thuc_thanh_toan BIT,
	tong_tien DECIMAL(18,0),
	ghi_chu NVARCHAR(MAX),
	phi_giao_hang DECIMAL(18,0),
	hinh_thuc_mua_hang BIT,
	dia_chi_giao_hang NVARCHAR(MAX),
    ngay_tao DATETIME DEFAULT GETDATE(),
    khach_hang_id INT,
	nguoi_dung_id INT,
	giam_gia_id INT,
    trang_thai NVARCHAR(100),
    FOREIGN KEY (khach_hang_id) REFERENCES Khach_hang(id),
	FOREIGN KEY (nguoi_dung_id) REFERENCES Nguoi_dung(id),
	FOREIGN KEY (giam_gia_id) REFERENCES Giam_gia(id)
);
GO
-- Bảng Hóa đơn chi tiết
CREATE TABLE hoa_don_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_hoa_don INT,
    san_pham_chi_tiet_id INT,
    so_luong INT NOT NULL,
    don_gia DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (id_hoa_don) REFERENCES Hoa_don(id),
    FOREIGN KEY (san_pham_chi_tiet_id) REFERENCES San_pham_chi_tiet(id)
);
GO
-- Bảng Giỏ hàng chi tiết
CREATE TABLE gio_hang_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_nguoi_dung INT,
    id_san_pham_chi_tiet INT,
    so_luong INT NOT NULL,
	ngay_tao DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_nguoi_dung) REFERENCES Nguoi_dung(id),
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES San_pham_chi_tiet(id)
);
GO
-- Bảng Khuyến mãi
CREATE TABLE khuyen_mai (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_khuyen_mai VARCHAR(10) NOT NULL,
    ten_khuyen_mai NVARCHAR(255) NOT NULL,
	phan_tram_giam INT NOT NULL,
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);

-- Thêm dữ liệu vào bảng Nhà cung cấp
INSERT INTO nha_cung_cap (ten_nha_cung_cap, dia_chi, so_dien_thoai, email, trang_thai, mo_ta)
VALUES 
('Nhà cung cấp A', 'Hà Nội', '0123456789', 'nccA@gmail.com', 1, 'Cung cấp sản phẩm thời trang'),
('Nhà cung cấp B', 'TP. Hồ Chí Minh', '0987654321', 'nccB@gmail.com', 1, 'Chuyên cung cấp nguyên liệu may mặc'),
('Nhà cung cấp C', 'Đà Nẵng', '0912345678', 'nccC@gmail.com', 0, 'Tạm ngừng hoạt động'),
('Nhà cung cấp D', 'Cần Thơ', '0934567890', 'nccD@gmail.com', 1, 'Nhà cung cấp lớn miền Tây'),
('Nhà cung cấp E', 'Hải Phòng', '0945678901', 'nccE@gmail.com', 1, 'Cung cấp vải cao cấp');

-- Thêm dữ liệu vào bảng Màu sắc
INSERT INTO mau_sac (ten_mau_sac, trang_thai, mo_ta)
VALUES 
('Đỏ', 1, 'Màu đỏ rực rỡ'),
('Xanh dương', 1, 'Màu xanh biển'),
('Vàng', 1, 'Màu vàng sáng'),
('Đen', 1, 'Màu đen huyền bí'),
('Trắng', 1, 'Màu trắng tinh khiết');

-- Thêm dữ liệu vào bảng Kích thước
INSERT INTO kich_thuoc (ten, trang_thai, mo_ta)
VALUES 
('S', 1, 'Size nhỏ'),
('M', 1, 'Size trung bình'),
('L', 1, 'Size lớn'),
('XL', 1, 'Size rất lớn'),
('XXL', 1, 'Size cực lớn');

-- Thêm dữ liệu vào bảng Chất liệu
INSERT INTO chat_lieu (ten, trang_thai, mo_ta)
VALUES 
('Cotton', 1, 'Chất liệu cotton cao cấp'),
('Polyester', 1, 'Chất liệu bền bỉ'),
('Lụa', 1, 'Chất liệu mềm mại'),
('Len', 1, 'Chất liệu ấm áp'),
('Jeans', 1, 'Chất liệu mạnh mẽ');

-- Thêm dữ liệu vào bảng Loại sản phẩm
INSERT INTO loai_san_pham (ten, trang_thai, mo_ta)
VALUES 
('Áo thun', 1, 'Sản phẩm áo thun phổ biến'),
('Quần dài', 1, 'Sản phẩm quần dài'),
('Áo sơ mi', 1, 'Sản phẩm áo sơ mi cao cấp'),
('Váy', 1, 'Sản phẩm váy thời trang'),
('Giày', 1, 'Sản phẩm giày thể thao');

-- Thêm dữ liệu vào bảng Tà áo
INSERT INTO ta_ao (ten, trang_thai, mo_ta)
VALUES 
('Ngắn', 1, 'Tà áo ngắn gọn gàng'),
('Dài', 1, 'Tà áo dài truyền thống'),
('Xẻ tà', 1, 'Tà áo xẻ phong cách'),
('Không tà', 1, 'Thiết kế không tà đơn giản'),
('Tà lửng', 1, 'Tà áo lửng hiện đại');

-- Thêm dữ liệu vào bảng Sản phẩm
INSERT INTO san_pham (ma_san_pham, ten_san_pham, mo_ta, loai_san_pham_id, nha_cung_cap_id, ta_ao_id, chat_lieu_id, trang_thai)
VALUES 
('SP001', 'Áo thun trắng', 'Áo thun cổ tròn', 1, 1, 1, 1, 1),
('SP002', 'Quần jeans xanh', 'Quần jeans thời trang', 2, 2, NULL, 5, 1),
('SP003', 'Áo sơ mi đen', 'Áo sơ mi phong cách', 3, 3, 4, 2, 1),
('SP004', 'Váy lụa vàng', 'Váy lụa cao cấp', 4, 4, NULL, 3, 1),
('SP005', 'Giày thể thao', 'Giày chạy bộ chất lượng cao', 5, 5, NULL, NULL, 1);

-- Thêm dữ liệu vào bảng Sản phẩm chi tiết
INSERT INTO san_pham_chi_tiet (ma_san_pham_chi_tiet, san_pham_id, mau_sac_id, gia_goc, gia_ban, kich_thuoc_id, so_luong, trang_thai)
VALUES 
('SPCT001', 1, 1, 100000, 120000, 2, 50, 1),
('SPCT002', 2, 2, 200000, 250000, 3, 30, 1),
('SPCT003', 3, 4, 150000, 180000, 4, 20, 1),
('SPCT004', 4, 3, 300000, 350000, 1, 15, 1),
('SPCT005', 5, 5, 500000, 550000, NULL, 10, 1);

-- Thêm dữ liệu vào bảng Ảnh
INSERT INTO anh (anh_url, id_san_pham, trang_thai)
VALUES 
('/images/sp001.jpg', 1, 1),
('/images/sp002.jpg', 2, 1),
('/images/sp003.jpg', 3, 1),
('/images/sp004.jpg', 4, 1),
('/images/sp005.jpg', 5, 1);

-- Thêm dữ liệu vào bảng Vai trò
INSERT INTO vai_tro (ten)
VALUES 
('Admin'),
('User'),
('Nhân viên bán hàng'),
('Nhân viên kho'),
('Khách hàng');

-- Thêm dữ liệu vào bảng Người dùng
INSERT INTO nguoi_dung (ma_nguoi_dung, ten_dang_nhap, ho_va_ten, gioi_tinh, ngay_sinh, email, mat_khau, so_dien_thoai, dia_chi, trang_thai, vai_tro_id)
VALUES 
('ND001', 'admin', 'Quản trị viên', 1, '1990-01-01', 'admin@gmail.com', '123456', '0123456789', 'Hà Nội', 1, 1),
('ND002', 'user1', 'Người dùng 1', 0, '1995-05-10', 'user1@gmail.com', '123456', '0987654321', 'Hồ Chí Minh', 1, 2),
('ND003', 'nvbanhang', 'Nhân viên bán hàng', 1, '1992-03-15', 'nvbh@gmail.com', '123456', '0912345678', 'Đà Nẵng', 1, 3),
('ND004', 'nvkho', 'Nhân viên kho', 0, '1998-07-20', 'nvkho@gmail.com', '123456', '0934567890', 'Hải Phòng', 1, 4),
('ND005', 'guest', 'Khách vãng lai', 1, '2000-01-01', 'guest@gmail.com', '123456', '0945678901', 'Cần Thơ', 1, 5);

-- Thêm dữ liệu vào các bảng khác nếu cần.



CREATE DATABASE DATN_GRACEFUL
GO
USE DATN_GRACEFUL
GO
-- Bảng Nhà cung cấp
CREATE TABLE nha_cung_cap (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten_nha_cung_cap NVARCHAR(255),
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
    ten_mau_sac NVARCHAR(255),
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Kích thước
CREATE TABLE kich_thuoc (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(50),
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Chất liệu
CREATE TABLE chat_lieu (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Loại sản phẩm
CREATE TABLE loai_san_pham (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Tà áo
CREATE TABLE ta_ao (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);
GO
-- Bảng Sản phẩm
CREATE TABLE san_pham (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_san_pham AS CONCAT('SP', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
    ten_san_pham NVARCHAR(255),
    mo_ta NVARCHAR(MAX),
    id_loai_san_pham INT,
    id_nha_cung_cap INT,
	id_ta_ao INT,
	id_chat_lieu INT,
	trang_thai BIT,
	ngay_tao DATETIME DEFAULT GETDATE()
    FOREIGN KEY (id_loai_san_pham) REFERENCES loai_san_pham(id),
    FOREIGN KEY (id_nha_cung_cap) REFERENCES nha_cung_cap(id),
	FOREIGN KEY (id_ta_ao) REFERENCES ta_ao(id),
    FOREIGN KEY (id_chat_lieu) REFERENCES chat_lieu(id)
);
GO
-- Bảng Sản phẩm chi tiết
CREATE TABLE san_pham_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_san_pham_chi_tiet AS CONCAT('SPCT', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
    id_san_pham INT,
    id_mau_sac INT,
	gia_goc DECIMAL(18,0),
	gia_ban DECIMAL(18,0),
    id_kich_thuoc INT,
    so_luong INT,
	trang_thai BIT,
	ngay_tao DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_san_pham) REFERENCES san_pham(id),
    FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(id),
    FOREIGN KEY (id_kich_thuoc) REFERENCES kich_thuoc(id),
);
GO
-- Bảng Ảnh
CREATE TABLE anh (
    id INT PRIMARY KEY IDENTITY(1,1),
    anh_url NVARCHAR(MAX),
    id_san_pham INT,
	ngay_tao DATETIME DEFAULT GETDATE(),
	trang_thai BIT
    FOREIGN KEY (id_san_pham) REFERENCES san_pham(id)
);
GO
-- Bảng Vai trò
CREATE TABLE vai_tro (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(50)
);
GO
-- Bảng Người nhân viên
CREATE TABLE nhan_vien (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_nhan_vien AS CONCAT('NV', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
	ten_dang_nhap VARCHAR(10),
    ho_va_ten NVARCHAR(255),
	gioi_tinh BIT,
	ngay_sinh DATE,
    email NVARCHAR(255),
    mat_khau NVARCHAR(255),
    so_dien_thoai NVARCHAR(20),
	dia_chi NVARCHAR(MAX),
	trang_thai BIT,
    id_vai_tro INT,
	ngay_tao DATETIME DEFAULT GETDATE(),
	FOREIGN KEY (id_vai_tro) REFERENCES vai_tro(id)
);
GO
-- Bảng Khách hàng
CREATE TABLE khach_hang (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_khach_hang AS CONCAT('KH', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
    ho_ten NVARCHAR(255),
	gioi_tinh BIT,
	ngay_sinh DATE,
    email NVARCHAR(255),
    so_dien_thoai NVARCHAR(20),
	ten_dang_nhap VARCHAR(10),
	mat_khau VARCHAR(50),
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
    id_khach_hang INT
	FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id)
);
GO
-- Bảng Giảm giá
CREATE TABLE giam_gia (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_giam_gia AS CONCAT('VOUCHER', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
    ten_giam_gia NVARCHAR(255),
    phan_tram_giam_gia INT,
	tien_giam_gia DECIMAL(18,0),
	toi_da_giam_gia DECIMAL(18,0),
	gia_tri_toi_thieu DECIMAL(18,0),
	so_luong INT,
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
	ma_hoa_don AS CONCAT('HD', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
	phuong_thuc_thanh_toan BIT,
	tong_tien DECIMAL(18,0),
	ghi_chu NVARCHAR(MAX),
	phi_giao_hang DECIMAL(18,0),
	hinh_thuc_mua_hang BIT,
	dia_chi_giao_hang NVARCHAR(MAX),
    ngay_tao DATETIME DEFAULT GETDATE(),
    id_khach_hang INT,
	id_nhan_vien INT,
	id_giam_gia INT,
    trang_thai NVARCHAR(100),
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id),
	FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(id),
	FOREIGN KEY (id_giam_gia) REFERENCES giam_gia(id)
);
GO
-- Bảng Hóa đơn chi tiết
CREATE TABLE hoa_don_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_hoa_don_chi_tiet AS CONCAT('HDCT', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
    id_hoa_don INT,
    id_san_pham_chi_tiet INT,
    so_luong INT,
    don_gia DECIMAL(18,2),
    FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id),
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id)
);
GO
-- Bảng Giỏ hàng chi tiết
CREATE TABLE gio_hang_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_nhan_vien INT,
    id_san_pham_chi_tiet INT,
    so_luong INT,
	ngay_tao DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(id),
    FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id)
);
GO
-- Bảng Khuyến mãi
CREATE TABLE khuyen_mai (
    id INT PRIMARY KEY IDENTITY(1,1),
	ma_khuyen_mai AS CONCAT('KM', RIGHT('000' + CAST(id AS VARCHAR(3)), 3)) PERSISTED,
    ten_khuyen_mai NVARCHAR(255),
	phan_tram_giam INT,
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
	trang_thai BIT,
	mo_ta NVARCHAR(MAX),
	ngay_tao DATETIME DEFAULT GETDATE(),
);


-- Bảng Nhà cung cấp
INSERT INTO nha_cung_cap (ten_nha_cung_cap, dia_chi, so_dien_thoai, email, trang_thai, mo_ta)
VALUES 
(N'Nhà cung cấp A', N'123 Đường A, Thành phố X', '0123456789', 'ncc_a@example.com', 1, N'Mô tả NCC A'),
(N'Nhà cung cấp B', N'456 Đường B, Thành phố Y', '0987654321', 'ncc_b@example.com', 1, N'Mô tả NCC B'),
(N'Nhà cung cấp C', N'789 Đường C, Thành phố Z', '0912345678', 'ncc_c@example.com', 1, N'Mô tả NCC C'),
(N'Nhà cung cấp D', N'321 Đường D, Thành phố X', '0934567890', 'ncc_d@example.com', 0, N'Mô tả NCC D'),
(N'Nhà cung cấp E', N'654 Đường E, Thành phố Y', '0967890123', 'ncc_e@example.com', 1, N'Mô tả NCC E');

-- Bảng Màu sắc
INSERT INTO mau_sac (ten_mau_sac, trang_thai, mo_ta)
VALUES 
(N'Đỏ', 1, N'Màu đỏ tươi'),
(N'Xanh', 1, N'Màu xanh biển'),
(N'Vàng', 1, N'Màu vàng nhạt'),
(N'Trắng', 1, N'Màu trắng tinh khôi'),
(N'Đen', 1, N'Màu đen huyền bí');

-- Bảng Kích thước
INSERT INTO kich_thuoc (ten, trang_thai, mo_ta)
VALUES 
('S', 1, N'Kích thước nhỏ'),
('M', 1, N'Kích thước vừa'),
('L', 1, N'Kích thước lớn'),
('XL', 1, N'Kích thước cực lớn'),
('XXL', 1, N'Kích thước rất lớn');

-- Bảng Chất liệu
INSERT INTO chat_lieu (ten, trang_thai, mo_ta)
VALUES 
(N'Cotton', 1, N'Vải cotton cao cấp'),
(N'Lụa', 1, N'Vải lụa mềm mịn'),
(N'Polyester', 1, N'Vải polyester chống nhăn'),
(N'Nỉ', 1, N'Vải nỉ ấm áp'),
(N'Denim', 1, N'Vải denim chắc chắn');

-- Bảng Loại sản phẩm
INSERT INTO loai_san_pham (ten, trang_thai, mo_ta)
VALUES 
(N'Áo dài cách tân', 1, N'Các loại áo dài truyền thống'),
(N'Áo dài truyền thống', 1,N'Các loại áo dài truyền thống'),
(N'Áo dài cổ điển', 1, N'Các loại áo dài truyền thống'),
(N'Áo dài hớ hênh', 1, N'Các loại áo dài truyền thống'),
(N'Áo dài hơn quần', 1, N'Các loại áo dài truyền thống');

-- Bảng Tà áo
INSERT INTO ta_ao (ten, trang_thai, mo_ta)
VALUES 
(N'Tà ngắn', 1, N'Tà áo ngắn dành cho thời trang hiện đại'),
(N'Tà dài', 1, N'Tà áo dài truyền thống'),
(N'Tà xẻ', 1, N'Tà áo xẻ độc đáo'),
(N'Tà tròn', 1, N'Tà áo tròn mềm mại'),
(N'Tà vuông', 1, N'Tà áo vuông cách tân');

-- Bảng Sản phẩm
INSERT INTO san_pham (ten_san_pham, mo_ta, id_loai_san_pham, id_nha_cung_cap, id_ta_ao, id_chat_lieu, trang_thai)
VALUES 
(N'Áo dài truyền thống', 'Áo dài Việt Nam, màu đỏ', 1, 1, 2, 1, 1),
(N'Áo dài ab', N'Áo dài Việt Nam', 2, 2, 1, 3, 1),
(N'Áo dài cd', N'Áo dài Việt Nam', 3, 3, 1, 4, 1),
(N'Áo dài ef', N'Áo dài Việt Nam', 4, 4, 1, 2, 1),
(N'Áo dài thật', N'Áo dài Việt Nam', 5, 5, 1, 5, 1);

-- Bảng Sản phẩm chi tiết
INSERT INTO san_pham_chi_tiet (id_san_pham, id_mau_sac, gia_goc, gia_ban, id_kich_thuoc, so_luong, trang_thai)
VALUES 
(1, 1, 500000, 550000, 1, 100, 1),
(2, 2, 300000, 350000, 2, 50, 1),
(3, 3, 150000, 170000, 3, 200, 1),
(4, 4, 250000, 280000, 4, 80, 1),
(5, 5, 400000, 450000, 5, 120, 1);

-- Bảng Ảnh
INSERT INTO anh (anh_url, id_san_pham, trang_thai)
VALUES 
('http://example.com/images/sp1.jpg', 1, 1),
('http://example.com/images/sp2.jpg', 2, 1),
('http://example.com/images/sp3.jpg', 3, 1),
('http://example.com/images/sp4.jpg', 4, 1),
('http://example.com/images/sp5.jpg', 5, 1);

-- Bảng Vai trò
INSERT INTO vai_tro (ten)
VALUES 
(N'Nhân viên'),
(N'Quản lý');

select *from vai_tro
-- Bảng Nhân viên
INSERT INTO nhan_vien (ten_dang_nhap, ho_va_ten, gioi_tinh, ngay_sinh, email, mat_khau, so_dien_thoai, dia_chi, trang_thai, id_vai_tro)
VALUES 
('abcde1', N'Nguyễn Văn A', 1, '1990-01-01', 'admin@example.com', 'password123', '0123456789', N'Hà Nội', 1, 1),
('abcde2', N'Trần Thị B', 0, '1995-02-15', 'nv01@example.com', 'password456', '0987654321', N'Hà Nội', 1, 2),
('abcde3', N'Lê Văn C', 1, '1988-03-20', 'kt01@example.com', 'password789', '0912345678', N'Hà Nội', 1, 1),
('abcde4', N'Phạm Thị D', 0, '1992-04-25', 'tk01@example.com', 'password000', '0934567890', N'Hà Nội', 1, 2),
('abcde5', N'Hoàng Văn E', 1, '1985-05-30', 'ql01@example.com', 'password111', '0945678901', N'Hà Nội', 1, 1);

-- Bảng Khách hàng
INSERT INTO khach_hang (ho_ten, gioi_tinh, ngay_sinh, email, so_dien_thoai, ten_dang_nhap, mat_khau, ngay_tao)
VALUES 
(N'Nguyễn Thị A', 0, '2000-06-15', 'khachhang_a@example.com', '0912345678', 'a123', 'password123', GETDATE()),
(N'Trần Văn B', 1, '1998-07-22', 'khachhang_b@example.com', '0923456789', 'b123', 'password456', GETDATE()),
(N'Lê Thị C', 0, '1995-08-30', 'khachhang_c@example.com', '0934567890', 'c123', 'password678', GETDATE()),
(N'Phạm Văn D', 1, '1993-09-18', 'khachhang_d@example.com', '0945678901', 'd123', 'password789', GETDATE()),
(N'Hoàng Thị E', 0, '2002-10-12', 'khachhang_e@example.com', '0956789012', 'e123', 'passwordJQK', GETDATE());

-- Bảng Địa chỉ
INSERT INTO dia_chi (duong, quan, thanh_pho, tinh, mac_dinh, id_khach_hang)
VALUES 
(N'123 Đường A', N'Quận 1', N'TPHCM', N'TPHCM', 1, 1),
(N'456 Đường B', N'Quận 2', N'TPHCM', N'TPHCM', 0, 2),
(N'789 Đường C', N'Quận 3', N'Hà Nội', N'Hà Nội', 1, 3),
(N'321 Đường D', N'Quận 4', N'Hà Nội', N'Hà Nội', 0, 4),
(N'654 Đường E', N'Quận 5', N'Đà Nẵng', N'Đà Nẵng', 1, 5);

-- Bảng Giảm giá
INSERT INTO giam_gia (ten_giam_gia, phan_tram_giam_gia, tien_giam_gia, toi_da_giam_gia, gia_tri_toi_thieu, so_luong, ngay_bat_dau, ngay_ket_thuc, trang_thai, mo_ta)
VALUES 
(N'Giảm 10%', 10, 10000, 50000, 200000, 100, '2025-01-01', '2025-02-01', 1, N'Giảm giá 10% cho đơn hàng trên 200000'),
(N'Giảm 20%', 20, 20000, 70000, 300000, 50, '2025-02-01', '2025-03-01', 1, N'Giảm giá 20% cho đơn hàng trên 300000'),
(N'Giảm 30%', 30, 30000, 100000, 500000, 30, '2025-03-01', '2025-04-01', 1, N'Giảm giá 30% cho đơn hàng trên 500000'),
(N'Giảm 15%', 15, 15000, 60000, 250000, 200, '2025-04-01', '2025-05-01', 1, N'Giảm giá 15% cho đơn hàng trên 250000'),
(N'Giảm 50%', 50, 50000, 150000, 1000000, 10, '2025-05-01', '2025-06-01', 1, N'Giảm giá 50% cho đơn hàng trên 1000000');

-- Bảng Hóa đơn
INSERT INTO hoa_don (phuong_thuc_thanh_toan, tong_tien, ghi_chu, phi_giao_hang, hinh_thuc_mua_hang, dia_chi_giao_hang, id_khach_hang, id_nhan_vien, id_giam_gia, trang_thai)
VALUES 
(1, 500000, N'abc', 30000, 1, N'123 Đường A, Quận 1, TP.HCM', 1, 1, 1, N'Đã thanh toán'),
(2, 700000, N'abc', 0, 2, N'456 Đường B, Quận 2, TP.HCM', 5, 1, 2, N'Đã thanh toán'),
(1, 300000, N'abc', 20000, 1, N'789 Đường C, Quận 3, Hà Nội', 2, 3, 3, N'Đã thanh toán'),
(2, 400000, N'abc', 25000, 2, N'321 Đường D, Quận 4, Hà Nội', 2, 4, 4, N'Đã thanh toán'),
(1, 600000, N'abc', 15000, 1, N'654 Đường E, Quận 5, Đà Nẵng', 3, 5, 5, N'Chưa thanh toán');


-- Bảng Hóa đơn chi tiết
INSERT INTO hoa_don_chi_tiet (id_hoa_don, id_san_pham_chi_tiet, so_luong, don_gia)
VALUES 
(1, 1, 1, 550000),
(2, 2, 2, 350000),
(3, 3, 3, 170000),
(4, 4, 1, 280000),
(5, 5, 2, 450000);

-- Bảng Giỏ hàng chi tiết
INSERT INTO gio_hang_chi_tiet (id_nhan_vien, id_san_pham_chi_tiet, so_luong)
VALUES 
(1, 1, 3),
(2, 2, 2),
(3, 3, 5),
(4, 4, 1),
(5, 5, 4);

-- Bảng Khuyến mãi
INSERT INTO khuyen_mai (ten_khuyen_mai, phan_tram_giam, ngay_bat_dau, ngay_ket_thuc, trang_thai, mo_ta)
VALUES 
(N'Khuyến mãi mùa xuân', 10, '2025-01-01', '2025-01-31', 1, N'Khuyến mãi đặc biệt mùa xuân'),
(N'Khuyến mãi Tết Nguyên Đán', 15, '2025-02-01', '2025-02-15', 1, N'Khuyến mãi Tết'),
(N'Khuyến mãi mùa hè', 20, '2025-06-01', '2025-06-30', 1, N'Khuyến mãi mùa hè 2025'),
(N'Khuyến mãi Black Friday', 25, '2025-11-01', '2025-11-30', 1, N'Giảm giá đặc biệt Black Friday'),
(N'Khuyến mãi Giáng Sinh', 30, '2025-12-01', '2025-12-25', 1, N'Giảm giá Giáng Sinh');







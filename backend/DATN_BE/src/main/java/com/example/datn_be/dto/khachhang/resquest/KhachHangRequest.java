package com.example.datn_be.dto.khachhang.resquest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangRequest {

    private Integer id;

    private String maKhachHang;

    @NotBlank(message = "Họ tên không được để trống")
    @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Họ tên chỉ được chứa chữ cái")
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    private String hoTen;

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gioiTinh;

    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate ngaySinh;

    @NotBlank(message = "Email không được để trống")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d._%+-]+@gmail\\.com$",
            message = "Email phải chứa cả chữ và số, và phải có đuôi @gmail.com"
    )
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(0|\\+84)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$",
            message = "Số điện thoại không hợp lệ. Phải là số điện thoại di động Việt Nam (10 số) với các đầu số: 03x, 05x, 07x, 08x, 09x"
    )
    private String soDienThoai;

    private LocalDate ngayTao;

    private List<DiaChiEditRequest> diaChis;
}

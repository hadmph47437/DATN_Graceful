package com.example.datn_be.dto.khachhang.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaChiEditRequest {

    private Integer id;

    private String duong;

    private String quan;

    private String thanhPho;

    private String tinh;

    private Boolean macDinh;
}

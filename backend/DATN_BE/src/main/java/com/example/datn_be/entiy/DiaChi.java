package com.example.datn_be.entiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dia_chi")
public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "duong")
    private String duong;

    @Column(name = "quan")
    private String quan;

    @Column(name = "thanh_pho")
    private String thanhPho;

    @Column(name = "tinh")
    private String tinh;

    @Column(name = "mac_dinh")
    private Boolean macDinh;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang",referencedColumnName = "id")
    @JsonBackReference
    private KhachHang khachHang;
}

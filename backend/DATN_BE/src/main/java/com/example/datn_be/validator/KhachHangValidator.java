package com.example.datn_be.validator;

import com.example.datn_be.dto.khachhang.resquest.KhachHangRequest;
import com.example.datn_be.repository.KhachHangRepo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class KhachHangValidator implements Validator {

    private final KhachHangRepo khachHangRepo;

    public KhachHangValidator(KhachHangRepo khachHangRepo) {
        this.khachHangRepo = khachHangRepo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return KhachHangRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        KhachHangRequest request = (KhachHangRequest) target;
        //check trùng mail
        if (khachHangRepo.existsByEmail(request.getEmail())){
            errors.rejectValue("email", "KhachHang.emailExist");
        }
        //check trùng sdt
        if (khachHangRepo.existsBySoDienThoai(request.getSoDienThoai())){
            errors.rejectValue("sdt", "KhachHang.sdtExist");
        }
        // Kiểm tra email đã tồn tại chưa (trừ chính bản ghi đang update)
        if (khachHangRepo.existsByEmailAndIdNot(request.getEmail(), request.getId() != null ? request.getId() : -1)) {
            errors.rejectValue("email", "KhachHang.emailExist");
        }
        // Kiểm tra số điện thoại đã tồn tại chưa (trừ chính bản ghi đang update)
        if (khachHangRepo.existsBySoDienThoaiAndIdNot(request.getSoDienThoai(), request.getId() != null ? request.getId() : -1)) {
            errors.rejectValue("soDienThoai", "KhachHang.idExist");
        }
    }
}

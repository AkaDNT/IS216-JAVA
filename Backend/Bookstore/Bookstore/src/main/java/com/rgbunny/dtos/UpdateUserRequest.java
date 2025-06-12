package com.rgbunny.dtos;

import com.rgbunny.entity.Address;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @Size(min = 2, max = 20, message = "Tên người dùng phải lớn hơn 2 và nhỏ hơn 20 ký tự!")
    private String userName;

    @Size(max = 50, message = "Email không được vượt quá 50 ký tự!")
    @Email(message = "Email không đúng định dạng!")
    private String email;

    @Size(min = 9, max = 15, message = "Số điện thoại phải có độ dài từ 9 đến 15 ký tự!")
    private String phoneNumber;
}

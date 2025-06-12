package com.rgbunny.dtos;

import com.rgbunny.entity.Address;
import com.rgbunny.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String phoneNumber;
    private String userName;
    private String email;
    private List<AddressDTO> addresses = new ArrayList<>();
    private String roles;
}

package com.rgbunny.service;

import com.rgbunny.dtos.UserResponse;
import com.rgbunny.entity.User;

import java.util.List;

public interface AdminService {
    List<UserResponse> GetAllUsers(Long id);
    List<UserResponse> GetAllCustomers(Long id);
    List<UserResponse> GetAllEmployees(Long id);
}

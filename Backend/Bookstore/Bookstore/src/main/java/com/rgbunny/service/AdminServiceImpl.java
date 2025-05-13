package com.rgbunny.service;

import com.rgbunny.dao.UserRepository;
import com.rgbunny.dtos.UserResponse;
import com.rgbunny.entity.Role;
import com.rgbunny.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserResponse> GetAllUsers(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return null;
        String appRoles = user.get().getRoles().stream()
                .map(role -> role.getRoleName().toString())
                .collect(Collectors.joining(","));
        if(!appRoles.contains("ROLE_ADMIN")) return null;
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> GetAllCustomers(Long id) {
        List<UserResponse> users = GetAllUsers(id);
        List<UserResponse> customers = users.stream().filter(user -> {
            String roles = user.getRoles().stream()
                    .map(role -> role.getRoleName().toString())
                    .collect(Collectors.joining(","));
            return (roles.contains("ROLE_USER"));
        }).toList();
        return customers;
    }

    @Override
    public List<UserResponse> GetAllEmployees(Long id) {
        List<UserResponse> users = GetAllUsers(id);
        List<UserResponse> employees = users.stream().filter(user -> {
            String roles = user.getRoles().stream()
                    .map(role -> role.getRoleName().toString())
                    .collect(Collectors.joining(","));
            return (roles.contains("ROLE_EMPLOYEES"));
        }).toList();
        return employees;
    }

    @Override
    public List<UserResponse> SearchUsersByName(Long id, String searchTerm) {
        List<UserResponse> users = GetAllUsers(id);
        if(searchTerm.isEmpty()) return users;
        List<UserResponse> result =  users.stream().filter(user -> {
            return user.getUserName().contains(searchTerm);
        }).toList();
        return result;
    }

    @Override
    public List<UserResponse> SearchEmployeesByName(Long id, String searchTerm) {
        List<UserResponse> users = GetAllEmployees(id);
        if(searchTerm.isEmpty()) return users;
        List<UserResponse> result =  users.stream().filter(user -> {
            return user.getUserName().contains(searchTerm);
        }).toList();
        return result;
    }

    @Override
    public List<UserResponse> SearchCustomersByName(Long id, String searchTerm) {
        List<UserResponse> users = GetAllCustomers(id);
        if(searchTerm.isEmpty()) return users;
        List<UserResponse> result =  users.stream().filter(user -> {
            return user.getUserName().contains(searchTerm);
        }).toList();
        return result;
    }
}

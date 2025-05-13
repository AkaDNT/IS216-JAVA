package com.rgbunny.service;

import com.rgbunny.dao.UserRepository;
import com.rgbunny.dtos.UpdateUserRequest;
import com.rgbunny.dtos.UserResponse;
import com.rgbunny.entity.AppRole;
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

    private Boolean CheckAdmin(Optional<User> user){
        String appRoles = user.get().getRoles().stream()
                .map(role -> role.getRoleName().toString())
                .collect(Collectors.joining(","));
        return appRoles.contains("ROLE_ADMIN");
    }

    @Override
    public List<UserResponse> GetAllUsers(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) return null;
        if(!CheckAdmin(user)) return null;
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

    @Override
    public UserResponse UpdateUserById(Long currentUserId, Long updatedUserID, UpdateUserRequest request) {
        Optional<User> currentUser = userRepository.findById(currentUserId);
        if(currentUser.isEmpty()) return null;
        if(!CheckAdmin(currentUser)) return null;
        User user = userRepository.findById(updatedUserID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.getUserName().equals(request.getUserName())){
            if(userRepository.existsByUserName(request.getUserName())) throw new RuntimeException("User name existed");
        }
        if(!user.getEmail().equals(request.getEmail())){
            if(userRepository.existsByEmail(request.getEmail())) throw new RuntimeException("Email existed");
        }
        if(!(request.getUserName()==null)) user.setUserName(request.getUserName());
        if(!(request.getEmail()==null)) user.setEmail(request.getEmail());

        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            Set<Role> newRoles = request.getRoles().stream()
                    .map(roleStr -> {
                        try {
                            AppRole appRole = AppRole.valueOf(roleStr);
                            return new Role(appRole);
                        } catch (IllegalArgumentException e) {
                            throw new RuntimeException("Invalid role: " + roleStr);
                        }
                    })
                    .collect(Collectors.toSet());
            user.setRoles(newRoles);
        }
        userRepository.save(user);

        return modelMapper.map(user, UserResponse.class);
    }
}

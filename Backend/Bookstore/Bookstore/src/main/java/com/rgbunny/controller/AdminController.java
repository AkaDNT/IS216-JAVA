package com.rgbunny.controller;

import com.rgbunny.dtos.UpdateUserRequest;
import com.rgbunny.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manage")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> GetAllUsers(@RequestParam Long userId){
        return ResponseEntity.ok(adminService.GetAllUsers(userId));
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<?> GetAllCustomers(@RequestParam Long userId){
        return ResponseEntity.ok(adminService.GetAllCustomers(userId));
    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<?> GetAllEmployees(@RequestParam Long userId){
        return ResponseEntity.ok(adminService.GetAllCustomers(userId));
    }

    @GetMapping("/search/users")
    public ResponseEntity<?> SearchUsersByName(@RequestParam Long userId, @RequestParam String searchTerm){
        return ResponseEntity.ok(adminService.SearchUsersByName(userId, searchTerm));
    }

    @GetMapping("/search/customers")
    public ResponseEntity<?> SearchCustomersByName(@RequestParam Long userId, @RequestParam String searchTerm){
        return ResponseEntity.ok(adminService.SearchCustomersByName(userId, searchTerm));
    }

    @GetMapping("/search/employees")
    public ResponseEntity<?> SearchEmployeesByName(@RequestParam Long userId, @RequestParam String searchTerm){
        return ResponseEntity.ok(adminService.SearchEmployeesByName(userId, searchTerm));
    }
    @PatchMapping("/user")
    public ResponseEntity<?> UpdateUserById(@RequestParam Long currentUserId, @RequestParam Long updatedUserId, @RequestBody UpdateUserRequest updateUserRequest){
        return ResponseEntity.ok(adminService.UpdateUserById(currentUserId,updatedUserId, updateUserRequest));
    }
}

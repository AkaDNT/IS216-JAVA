package com.rgbunny.controller;

import com.rgbunny.dtos.UpdateUserRequestForAdmin;
import com.rgbunny.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manage")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> GetAllUsers(Authentication authentication){
        return ResponseEntity.ok(adminService.GetAllUsers(authentication));
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<?> GetAllCustomers(Authentication authentication){
        return ResponseEntity.ok(adminService.GetAllCustomers(authentication));
    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<?> GetAllEmployees(Authentication authentication){
        return ResponseEntity.ok(adminService.GetAllEmployees(authentication));
    }

    @GetMapping("/search/users")
    public ResponseEntity<?> SearchUsersByName(Authentication authentication, @RequestParam String searchTerm){
        return ResponseEntity.ok(adminService.SearchUsersByName(authentication, searchTerm));
    }

    @GetMapping("/search/customers")
    public ResponseEntity<?> SearchCustomersByName(Authentication authentication, @RequestParam String searchTerm){
        return ResponseEntity.ok(adminService.SearchCustomersByName(authentication, searchTerm));
    }

    @GetMapping("/search/employees")
    public ResponseEntity<?> SearchEmployeesByName(Authentication authentication, @RequestParam String searchTerm){
        return ResponseEntity.ok(adminService.SearchEmployeesByName(authentication, searchTerm));
    }
    @PatchMapping("/user")
    public ResponseEntity<?> UpdateUserById(Authentication authentication, @RequestParam Long updatedUserId, @RequestBody UpdateUserRequestForAdmin updateUserRequest){
        return ResponseEntity.ok(adminService.UpdateUserById(authentication,updatedUserId, updateUserRequest));
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> DeleteUserById(@PathVariable Long id){
        adminService.DeleteUserById(id);
        return new ResponseEntity<>("Delete user successfully", HttpStatus.NO_CONTENT);
    }
}

package com.rgbunny.controller;

import com.rgbunny.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manage")
public class AdminController {
    @Autowired
    AdminService profileService;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> GetAllUsers(@RequestParam Long userId){
        return ResponseEntity.ok(profileService.GetAllUsers(userId));
    }

    @GetMapping("/get-all-customers")
    public ResponseEntity<?> GetAllCustomers(@RequestParam Long userId){
        return ResponseEntity.ok(profileService.GetAllCustomers(userId));
    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<?> GetAllEmployees(@RequestParam Long userId){
        return ResponseEntity.ok(profileService.GetAllCustomers(userId));
    }
}

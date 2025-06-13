package com.rgbunny.controller;

import com.rgbunny.dtos.OrderResponse;
import com.rgbunny.dtos.UpdateUserRequest;
import com.rgbunny.dtos.UserResponse;
import com.rgbunny.entity.User;
import com.rgbunny.service.OrderService;
import com.rgbunny.service.UserService;
import com.rgbunny.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    AuthUtil authUtil;

    @GetMapping("/me")
    public ResponseEntity<?>GetMe(Authentication authentication){
        UserResponse userResponse = userService.GetMe(authentication);
        if(userResponse == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponse>>GetMyOrders(){
        User user = authUtil.loggedInUser();
        List<OrderResponse>orderResponses = orderService.getAllMyOrder(user.getId());
        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }

    @PatchMapping("me")
    public ResponseEntity<UserResponse>UpdateMe(@RequestBody UpdateUserRequest updateUserRequest){
        UserResponse userResponse = userService.UpdateUser(updateUserRequest);
        if(userResponse == null) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}

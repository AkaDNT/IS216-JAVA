package com.rgbunny.service;

import com.rgbunny.dao.UserRepository;
import com.rgbunny.dtos.AddressDTO;
import com.rgbunny.dtos.UpdateUserRequest;
import com.rgbunny.dtos.UserResponse;
import com.rgbunny.entity.User;
import com.rgbunny.exceptions.APIException;
import com.rgbunny.utils.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthUtil authUtil;

    @Override
    public UserResponse GetMe(Authentication authentication) {
        String userName = authentication.getName();
        Optional<User> user = userRepository.findByUserName(userName);
        UserResponse userResponse = modelMapper.map(user,UserResponse.class);
        List<AddressDTO> addressDTOList = user.get().getAddresses().stream().map(address -> {
            return modelMapper.map(address, AddressDTO.class);
        }).toList();
        userResponse.setAddresses(addressDTOList);
        String roles = user.get().getRoles().stream()
                .map(role -> role.getRoleName().toString())
                .collect(Collectors.joining(","));
        userResponse.setRoles(roles);
        return userResponse;
    }

    @Override
    public UserResponse UpdateUser(UpdateUserRequest updateUserRequest) {
        User user = authUtil.loggedInUser();
        if(user == null) throw new APIException("Please log in before use this action");
        modelMapper.map(updateUserRequest, user);
        System.out.println(updateUserRequest);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponse.class);
    }
}

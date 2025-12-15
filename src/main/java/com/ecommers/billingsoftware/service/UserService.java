package com.ecommers.billingsoftware.service;

import com.ecommers.billingsoftware.io.UserRequest;
import com.ecommers.billingsoftware.io.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    String getUserRole(String email);
    List<UserResponse> readUsers();
    void deleteUser(String id);
}

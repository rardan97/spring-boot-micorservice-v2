package com.blackcode.user_service.service;

import com.blackcode.user_service.dto.UserReq;
import com.blackcode.user_service.dto.UserRes;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserRes> getAllUser();

    UserRes getUserById(Long userId);

    UserRes addUser(UserReq userReq);

    UserRes updateUser(Long userId, UserReq userReq);

    Map<String, Object> deleteUser(Long userId);

}

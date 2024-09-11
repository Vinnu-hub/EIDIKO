package com.javatalent.service;

import com.javatalent.dto.ResponseDto;
import com.javatalent.entity.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);

}

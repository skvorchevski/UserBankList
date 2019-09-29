package com.gmail.askvorchevski.service.converter.impl;

import com.gmail.askvorchevski.repository.model.User;
import com.gmail.askvorchevski.service.converter.UserConverter;
import com.gmail.askvorchevski.service.model.UserDTO;

public class UserConverterImpl implements UserConverter {
    private static UserConverterImpl instance;

    private UserConverterImpl() {
    }

    public static UserConverterImpl getInstance() {
        if (instance == null) {
            instance = new UserConverterImpl();
        }
        return instance;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setSureName(user.getSureName());
        return userDTO;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setSureName(userDTO.getSureName());
        return user;
    }
}

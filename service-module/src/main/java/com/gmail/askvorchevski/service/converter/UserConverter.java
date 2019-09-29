package com.gmail.askvorchevski.service.converter;

import com.gmail.askvorchevski.repository.model.User;
import com.gmail.askvorchevski.service.model.UserDTO;

public interface UserConverter {
    UserDTO toDTO(User user);

    User fromDTO(UserDTO userDTO);
}

package com.gmail.askvorchevski.service.converter.impl;

import com.gmail.askvorchevski.repository.model.User;
import com.gmail.askvorchevski.service.converter.UserConverter;
import com.gmail.askvorchevski.service.model.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserConverterImplTest {
    private UserConverter userConverter;

    @Before
    public void setUp() throws Exception {
        userConverter = UserConverterImpl.getInstance();
    }

    @Test
    public void ShouldReturnUserID() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);
        User user = userConverter.fromDTO(userDTO);
        assertEquals(user.getUserId(), userDTO.getUserId());
    }

    @Test
    public void ShouldReturnUserName() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Andrew");
        User user = userConverter.fromDTO(userDTO);
        assertEquals(user.getName(), userDTO.getName());
    }

    @Test
    public void ShouldReturnUserSurname() {
        UserDTO userDTO = new UserDTO();
        userDTO.setSureName("Surname");
        User user = userConverter.fromDTO(userDTO);
        assertEquals(user.getSureName(), userDTO.getSureName());
    }

    @Test
    public void ShouldReturnUserDTOid() {
        User user = new User();
        user.setUserId(1);
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(userDTO.getSureName(), user.getSureName());
    }

    @Test
    public void ShouldReturnUserDTOName() {
        User user = new User();
        user.setName("Name");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(userDTO.getName(), user.getName());
    }

    @Test
    public void ShouldReturnUserDTOSurname() {
        User user = new User();
        user.setSureName("Surname");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(userDTO.getSureName(), user.getSureName());
    }
}
package com.metryfabricshopnew.service;

import com.metryfabricshopnew.domain.User;
import com.metryfabricshopnew.dto.UserDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//security
public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);

    void save(User user);

    List<UserDTO> getAll();

    User findByName(String name);

    void updateProfile(UserDTO userDTO);
}

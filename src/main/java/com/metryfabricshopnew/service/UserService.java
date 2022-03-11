package com.metryfabricshopnew.service;

import com.metryfabricshopnew.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

//security
public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
}

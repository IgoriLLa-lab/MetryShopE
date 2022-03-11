package com.metryfabricshopnew.repository;

import com.metryfabricshopnew.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}

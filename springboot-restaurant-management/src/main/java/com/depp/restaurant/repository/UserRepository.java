package com.depp.restaurant.repository;

import com.depp.restaurant.entity.User;
import com.depp.restaurant.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);

    User findUserByUserRole(UserRole admin);
}

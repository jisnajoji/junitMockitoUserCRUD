package com.example.mockitoUserCrud.repository;

import com.example.mockitoUserCrud.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
}

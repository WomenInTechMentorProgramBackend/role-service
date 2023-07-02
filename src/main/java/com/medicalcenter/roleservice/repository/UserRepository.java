package com.medicalcenter.roleservice.repository;

import com.medicalcenter.roleservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserRepository extends JpaRepository<User, UUID> {
}

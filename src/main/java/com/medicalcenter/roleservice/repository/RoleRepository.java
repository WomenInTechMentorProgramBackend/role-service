package com.medicalcenter.roleservice.repository;

import com.medicalcenter.roleservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}

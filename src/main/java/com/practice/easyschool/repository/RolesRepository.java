package com.practice.easyschool.repository;

import com.practice.easyschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles getByRoleName(String studentRole);
}
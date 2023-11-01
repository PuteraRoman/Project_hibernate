package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.service.impl.RoleServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

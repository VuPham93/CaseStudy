package com.codegym.Casestudy.repository;

import com.codegym.Casestudy.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}

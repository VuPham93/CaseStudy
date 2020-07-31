package com.codegym.Casestudy.service.role;

import com.codegym.Casestudy.model.user.Role;
import com.codegym.Casestudy.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService{
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return null;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void save(Role model) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}

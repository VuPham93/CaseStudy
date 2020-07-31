package com.codegym.Casestudy.service.role;

import com.codegym.Casestudy.model.user.Role;
import com.codegym.Casestudy.service.IService;

public interface IRoleService extends IService<Role> {
    Role findByName(String name);
}

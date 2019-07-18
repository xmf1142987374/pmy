package hb.xm.service;

import hb.xm.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRole();

    public void addRole(Role role);
}
